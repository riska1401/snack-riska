package com.example.ika_biskuat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ika_biskuat.databinding.ActivityRegisterGmailBinding

class RegisterGmailActivity : AppCompatActivity() {

    // Deklarasi variabel binding di tingkat class
    private lateinit var binding: ActivityRegisterGmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterGmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmitGmail.setOnClickListener {
            val email = binding.etGmailInput.text.toString().trim()

            // Validasi Soal 1
            when {
                email.isEmpty() -> {
                    showErrorDialog("Email tidak boleh kosong!")
                }
                !email.endsWith("@gmail.com", ignoreCase = true) -> {
                    showErrorDialog("Email harus menggunakan domain @gmail.com!")
                }
                else -> {
                    // Pindah ke RegisterActivity membawa data email
                    val intent = Intent(this, RegisterActivity::class.java).apply {
                        putExtra("EXTRA_EMAIL", email)
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    // Gunakan 'fun', BUKAN 'function'
    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Validasi Gagal")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}