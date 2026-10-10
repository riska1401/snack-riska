package com.example.ika_biskuat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ika_biskuat.databinding.ActivityOnboarding2Binding

class Onboarding2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboarding2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboarding2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Klik tombol untuk lanjut ke WebActivity
        binding.btnNext2.setOnClickListener {
            startActivity(Intent(this, WebActivity::class.java))
        }
    }
}