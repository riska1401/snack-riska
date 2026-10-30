package com.example.ika_biskuat.report

import android.app.DatePickerDialog
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

    private var selectedDateStr: String = "2026-10-16"

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

        // Tampilkan tanggal default
        binding.tvSelectedDate.text = selectedDateStr

        // Tombol Pilih Tanggal (Memicu Dialog Kalender Pop-up Bawaan Android yang Rapi)
        binding.btnSelectDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Aksi Tombol Ekspor PDF
        binding.btnExportPdf.setOnClickListener {
            generatePdfReport()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                selectedDateStr = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                binding.tvSelectedDate.text = selectedDateStr
                Toast.makeText(requireContext(), "Periode dipilih: $selectedDateStr", Toast.LENGTH_SHORT).show()
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun generatePdfReport() {
        val headers = listOf("Tanggal", "Kategori", "Keterangan", "Jumlah")
        val dataRows = listOf(
            listOf(selectedDateStr, "Panen TBS", "Blok A Utama", "2500 Kg"),
            listOf(selectedDateStr, "Pupuk NPK", "Subsidi KUD", "Rp 500k"),
            listOf(selectedDateStr, "Panen TBS", "Blok B", "1800 Kg")
        )

        PdfExporter.exportToPdf(
            context = requireContext(),
            fileName = "Laporan_SEMAWIT_$selectedDateStr",
            title = "LAPORAN KEBUN SEMAWIT ($selectedDateStr)",
            headers = headers,
            dataRows = dataRows
        )

        Toast.makeText(requireContext(), "PDF Berhasil Diekspor!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}