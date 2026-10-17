package com.example.ika_biskuat.categories

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ika_biskuat.Onboarding1Activity
import com.example.ika_biskuat.R
import com.example.ika_biskuat.WebActivity
import com.example.ika_biskuat.databinding.FragmentCategoriesBinding
import com.example.ika_biskuat.home.HomeFragment
import com.example.ika_biskuat.pertemuan3.DetailBiskuatActivity

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Input Panen (Numpad)
        binding.btnInputPanen.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Form Input Panen SEMAWIT", Toast.LENGTH_SHORT).show()
        }

        // 2. Onboarding
        binding.btnToOnboarding.setOnClickListener {
            startActivity(Intent(requireContext(), Onboarding1Activity::class.java))
        }

        // 3. WebActivity
        binding.btnToWeb.setOnClickListener {
            startActivity(Intent(requireContext(), WebActivity::class.java))
        }

        // 4. Detail Info
        binding.btnToDetail.setOnClickListener {
            startActivity(Intent(requireContext(), DetailBiskuatActivity::class.java))
        }

        // 5. Data Lahan
        binding.btnDataLahan.setOnClickListener {
            Toast.makeText(requireContext(), "Lahan Kebun SEMAWIT: 2 Hektar", Toast.LENGTH_SHORT).show()
        }
        binding.btnBack.setOnClickListener {
            // Berpindah langsung ke halaman Home (menggunakan NavController atau FragmentManager sesuai navigasi projectmu)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()) // Ganti HomeFragment() dengan nama class Home kamu jika berbeda
                .commit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}