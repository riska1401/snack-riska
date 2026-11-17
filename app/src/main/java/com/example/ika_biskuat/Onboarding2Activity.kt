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

        binding.btnNext2.setOnClickListener {
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            sharedPref.edit().putBoolean("hasSeenOnboarding", true).apply()

            val isLogin = sharedPref.getBoolean("isLogin", false)
            val nextIntent = if (isLogin) {
                Intent(this, BaseActivity::class.java)
            } else {
                Intent(this, AuthActivity::class.java)
            }
            startActivity(nextIntent)
            finish()
        }
    }
}