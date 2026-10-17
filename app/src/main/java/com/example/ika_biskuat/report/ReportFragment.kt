package com.example.ika_biskuat.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ika_biskuat.R
// Pastikan import HomeFragment sesuai dengan lokasi folder project kamu, misal:
// import com.example.ika_biskuat.home.HomeFragment

class ReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tombol kembali diarahkan ke Home
        val btnBack = view.findViewById<View>(R.id.btnBack)
        btnBack?.setOnClickListener {
            // Jika menggunakan FragmentManager manual:
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, com.example.ika_biskuat.home.HomeFragment()) // Ganti dengan class Home Fragment kamu
                .commit()
        }
    }
}