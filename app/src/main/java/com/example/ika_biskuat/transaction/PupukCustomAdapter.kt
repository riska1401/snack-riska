package com.example.ika_biskuat.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ika_biskuat.R

data class PupukItem(
    val nama: String,
    val harga: String,
    val iconRes: Int
)

class PupukCustomAdapter(
    private val context: Context,
    private val listPupuk: List<PupukItem>
) : BaseAdapter() {

    override fun getCount(): Int = listPupuk.size

    override fun getItem(position: Int): Any = listPupuk[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_pupuk_grid, parent, false)

        val item = listPupuk[position]
        val ivImage = view.findViewById<ImageView>(R.id.ivPupukImage)
        val tvNama = view.findViewById<TextView>(R.id.tvPupukNama)
        val tvHarga = view.findViewById<TextView>(R.id.tvPupukHarga)

        ivImage.setImageResource(item.iconRes)
        tvNama.text = item.nama
        tvHarga.text = item.harga

        return view
    }
}