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

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validasi: Username dan Password harus diisi dan nilainya sama
            if (username.isNotEmpty() && username == password) {
                // Simpan Sesi Login di SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                Toast.makeText(this, "Login Berhasil! Selamat Datang $username", Toast.LENGTH_SHORT).show()

                // Pindah ke BaseActivity (Container Utama dengan Bottom Navigation SEMAWIT)
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish() // Menutup AuthActivity
            } else {
                // Tampilkan Dialog Peringatan Jika Login Gagal
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username dan Password tidak sesuai! Harap periksa kembali.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}