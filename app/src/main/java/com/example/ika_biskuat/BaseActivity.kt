package com.example.ika_biskuat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ika_biskuat.categories.CategoriesFragment
import com.example.ika_biskuat.databinding.ActivityBaseBinding
import com.example.ika_biskuat.home.HomeFragment
import com.example.ika_biskuat.profile.ProfileFragment
import com.example.ika_biskuat.report.ReportFragment
import com.example.ika_biskuat.transaction.TransactionFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set HomeFragment sebagai layar default saat aplikasi dibuka
        replaceFragment(HomeFragment())

        // Listener klik ikon Bottom Navigation
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_categories -> {
                    replaceFragment(CategoriesFragment())
                    true
                }
                R.id.nav_transaction -> {
                    replaceFragment(TransactionFragment())
                    true
                }
                R.id.nav_report -> {
                    replaceFragment(ReportFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Fungsi pembantu untuk mengganti Fragment di container
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}