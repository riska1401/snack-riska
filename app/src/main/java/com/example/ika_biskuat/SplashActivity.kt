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

        // Delay 2 detik untuk efek Splash Screen
        Handler(Looper.getMainLooper()).postDelayed({
            checkUserSession()
        }, 2000)
    }

    private fun checkUserSession() {
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            // Jika sudah login, langsung buka BaseActivity (Bottom Navigation SEMAWIT)
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
        } else {
            // Jika belum login, arahkan ke Halaman Auth/Login
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
        finish() // Menutup SplashActivity agar tidak bisa di-back
    }
}