package com.example.ika_biskuat.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ika_biskuat.BaseActivity
import com.example.ika_biskuat.R

class TransactionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<View>(R.id.btnBack)
        btnBack?.setOnClickListener {
            (requireActivity() as? BaseActivity)?.setSelectedBottomNavItem(R.id.nav_home)
        }
    }
}