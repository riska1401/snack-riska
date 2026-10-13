package com.example.ika_biskuat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ika_biskuat.databinding.ActivityMainBinding
import com.example.ika_biskuat.pertemuan3.DetailBiskuatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Ke Onboarding
        binding.btnStartOnboarding.setOnClickListener {
            startActivity(Intent(this, Onboarding1Activity::class.java))
        }

        // 2. Ke Project Lama (Detail Biskuat)
        binding.btnMyProject.setOnClickListener {
            startActivity(Intent(this, DetailBiskuatActivity::class.java))
        }

        // 3. Ke Web Activity
        binding.btnOpenWeb.setOnClickListener {
            startActivity(Intent(this, WebActivity::class.java))
        }

        // 4. Ke Profil Developer
        binding.btnDeveloper.setOnClickListener {
            startActivity(Intent(this, DeveloperActivity::class.java))
        }

        // 5. Fitur Logout (Hapus SharedPreferences)
        binding.btnLogout.setOnClickListener {
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()

                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}