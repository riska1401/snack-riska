package com.example.ika_biskuat

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ika_biskuat.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol Login Utama
        binding.btnLogin.setOnClickListener {
            val usernameInput = binding.etUsername.text.toString().trim()
            val passwordInput = binding.etPassword.text.toString().trim()

            // Ambil data yang tersimpan di SharedPreferences dari hasil registrasi
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val savedUsername = sharedPref.getString("reg_username", null)
            val savedPassword = sharedPref.getString("reg_password", null)

            // Kondisi Login:
            // - Kondisi A: Sesuai dengan data registrasi SharedPreferences
            val isRegisteredLogin = (usernameInput == savedUsername && passwordInput == savedPassword && savedUsername != null)
            // - Kondisi B: Sesuai dengan login bawaan proyek awal (username == password)
            val isDefaultLogin = (usernameInput.isNotEmpty() && usernameInput == passwordInput)

            if (isRegisteredLogin || isDefaultLogin) {
                // Simpan status sesi login aktif
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", usernameInput)
                editor.apply()

                Toast.makeText(this, "Login Berhasil! Selamat Datang $usernameInput", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman utama BaseActivity (Bottom Navigation SEMAWIT)
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish() // Menutup AuthActivity agar tidak bisa di-back
            } else {
                // Tampilkan pesan error menggunakan MaterialAlertDialog jika login gagal
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username dan Password tidak sesuai! Harap periksa kembali.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        // 2. Tombol Navigasi ke Halaman "Register With Gmail"
        binding.btnGoToRegister?.setOnClickListener {
            val intent = Intent(this, RegisterGmailActivity::class.java)
            startActivity(intent)
        }
    }
}