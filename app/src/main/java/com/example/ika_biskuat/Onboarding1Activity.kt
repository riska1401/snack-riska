package com.example.ika_biskuat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ika_biskuat.databinding.ActivityOnboarding1Binding

class Onboarding1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboarding1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboarding1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Klik tombol Next untuk pindah ke Onboarding 2
        binding.btnNext1.setOnClickListener {
            startActivity(Intent(this, Onboarding2Activity::class.java))
        }
    }
}