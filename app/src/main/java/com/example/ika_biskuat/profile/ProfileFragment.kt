package com.example.ika_biskuat.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.ika_biskuat.AuthActivity
import com.example.ika_biskuat.BaseActivity
import com.example.ika_biskuat.DeveloperActivity
import com.example.ika_biskuat.R
import com.example.ika_biskuat.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Profil Developer
        binding.btnProfilDeveloper.setOnClickListener {
            startActivity(Intent(requireContext(), DeveloperActivity::class.java))
        }

        // Tombol Logout
        binding.btnLogoutAkun.setOnClickListener {
            showLogoutConfirmation()
        }

        // Tombol Back Kembali ke Home
        binding.btnBack.setOnClickListener {
            (requireActivity() as? BaseActivity)?.setSelectedBottomNavItem(R.id.nav_home)
        }
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Keluar")
            .setMessage("Apakah Anda yakin ingin keluar dari akun SEMAWIT?")
            .setPositiveButton("Ya, Keluar") { _, _ ->
                val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sharedPref.edit().clear().apply()

                Toast.makeText(requireContext(), "Berhasil Logout", Toast.LENGTH_SHORT).show()

                val intent = Intent(requireContext(), AuthActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}