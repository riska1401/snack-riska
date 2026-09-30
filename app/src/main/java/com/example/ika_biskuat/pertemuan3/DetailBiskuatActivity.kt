package com.example.ika_biskuat.pertemuan3

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.ika_biskuat.R
import com.example.ika_biskuat.databinding.ActivityDetailBiskuatBinding

class DetailBiskuatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBiskuatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding
        binding = ActivityDetailBiskuatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengubah warna dan judul AppBar agar senada dengan warna Biskuat
        supportActionBar?.title = "Detail Produk Biskuat"
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.biskuat_red))
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}