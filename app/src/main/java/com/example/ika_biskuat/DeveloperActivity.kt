package com.example.ika_biskuat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ika_biskuat.databinding.ActivityDeveloperBinding

class DeveloperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeveloperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeveloperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDev)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarDev.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}