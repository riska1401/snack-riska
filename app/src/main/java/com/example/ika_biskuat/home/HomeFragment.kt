package com.example.ika_biskuat.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ika_biskuat.BaseActivity
import com.example.ika_biskuat.R
import com.example.ika_biskuat.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val baseActivity = requireActivity() as? BaseActivity

        binding.gridCardPupuk.setOnClickListener {
            baseActivity?.setSelectedBottomNavItem(R.id.nav_transaction)
        }

        binding.gridCardLaporan.setOnClickListener {
            baseActivity?.setSelectedBottomNavItem(R.id.nav_report)
        }

        binding.gridCardKategori.setOnClickListener {
            baseActivity?.setSelectedBottomNavItem(R.id.nav_categories)
        }

        binding.gridCardProfil.setOnClickListener {
            baseActivity?.setSelectedBottomNavItem(R.id.nav_profile)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}