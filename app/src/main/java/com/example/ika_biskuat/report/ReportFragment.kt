package com.example.ika_biskuat.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ika_biskuat.BaseActivity
import com.example.ika_biskuat.R
import com.example.ika_biskuat.databinding.FragmentReportBinding
import java.util.Calendar

class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    private var selectedDateStr: String = "Semua Tanggal"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tombol Kembali ke Home
        binding.btnBack.setOnClickListener {
            (requireActivity() as? BaseActivity)?.setSelectedBottomNavItem(R.id.nav_home)
        }

        // Inisialisasi Tanggal Default Hari Ini
        val calendar = Calendar.getInstance()
        selectedDateStr = String.format("%04d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
        binding.tvSelectedDate.text = "Periode: $selectedDateStr"

        // Listener Kalender Interaktif
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDateStr = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
            binding.tvSelectedDate.text = "Periode: $selectedDateStr"
            Toast.makeText(requireContext(), "Tanggal dipilih: $selectedDateStr", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}