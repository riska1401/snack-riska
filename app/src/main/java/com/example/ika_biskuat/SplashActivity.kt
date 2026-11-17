package com.example.ika_biskuat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.ika_biskuat.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            checkUserSession()
        }, 2000)
    }

    private fun checkUserSession() {
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)
        val hasSeenOnboarding = sharedPref.getBoolean("hasSeenOnboarding", false)

        val intent = when {
            !hasSeenOnboarding -> Intent(this, Onboarding1Activity::class.java)
            isLogin -> Intent(this, BaseActivity::class.java)
            else -> Intent(this, AuthActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}