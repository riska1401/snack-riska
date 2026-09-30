package com.example.ika_biskuat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ika_biskuat.databinding.ActivityMainBinding
import com.example.ika_biskuat.pertemuan3.DetailBiskuatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Berpindah activity menggunakan Intent ketika tombol ditekan
        binding.btnMyProject.setOnClickListener {
            val intent = Intent(this, DetailBiskuatActivity::class.java)
            startActivity(intent)
        }
    }
}