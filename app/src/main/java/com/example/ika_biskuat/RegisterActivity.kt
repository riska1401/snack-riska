package com.example.ika_biskuat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ika_biskuat.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menerima data email yang dibawa secara otomatis dari GmailActivity[cite: 35]
        val incomeEmail = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        binding.etRegEmail.setText(incomeEmail)

        binding.btnRegisterSubmit.setOnClickListener {
            val name = binding.etRegName.text.toString().trim()
            val email = binding.etRegEmail.text.toString().trim()
            val username = binding.etRegUsername.text.toString().trim()
            val password = binding.etRegPassword.text.toString().trim()

            // Validasi Registrasi (Soal 2)
            when {
                name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() -> {
                    showError("Semua field wajib diisi!")
                }
                username.contains(" ") -> {
                    showError("Username tidak boleh mengandung spasi!")
                }
                password.length < 6 -> {
                    showError("Password minimal harus 6 karakter!")
                }
                else -> {
                    // Simpan seluruh data registrasi ke SharedPreferences[cite: 36]
                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("reg_name", name)
                        putString("reg_email", email)
                        putString("reg_username", username)
                        putString("reg_password", password)
                        apply()
                    }

                    // Tampilkan halaman informasi "Registrasi Berhasil"[cite: 36]
                    val intent = Intent(this, RegisterSuccessActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun showError(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Validasi Gagal")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}