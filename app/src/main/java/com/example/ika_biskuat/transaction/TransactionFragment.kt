package com.example.ika_biskuat.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.ika_biskuat.BaseActivity
import com.example.ika_biskuat.R
import com.example.ika_biskuat.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            (requireActivity() as? BaseActivity)?.setSelectedBottomNavItem(R.id.nav_home)
        }

        // Data Katalog Pupuk KUD Mitra
        val daftarPupuk = listOf(
            PupukItem("Pupuk NPK 16-16", "Rp 450.000 / sak", R.drawable.logosemawit),
            PupukItem("Pupuk Urea", "Rp 380.000 / sak", R.drawable.logosemawit),
            PupukItem("Pupuk KCL", "Rp 410.000 / sak", R.drawable.logosemawit),
            PupukItem("Pupuk Dolomit", "Rp 185.000 / sak", R.drawable.logosemawit)
        )

        // Pasang CustomAdapter ke GridView
        val adapter = PupukCustomAdapter(requireContext(), daftarPupuk)
        binding.gridViewPupuk.adapter = adapter

        binding.gridViewPupuk.setOnItemClickListener { _, _, position, _ ->
            val terpilih = daftarPupuk[position]
            AlertDialog.Builder(requireContext())
                .setTitle("Tukar Saldo Pupuk (30%)")
                .setMessage("Ajukan penukaran ${terpilih.nama} (${terpilih.harga}) ke KUD Mitra?")
                .setPositiveButton("Ajukan") { _, _ ->
                    Toast.makeText(requireContext(), "Pengajuan ${terpilih.nama} terkirim ke KUD!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Batal", null)
                .show()
        }

        binding.btnAddMorePupuk.setOnClickListener {
            Toast.makeText(requireContext(), "Silakan pilih jenis pupuk pada katalog di atas", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}