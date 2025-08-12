package com.galfinilhami.makananbergizi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.galfinilhami.makananbergizi.data.MenuHarian

class MenuHarianAdapter(
    private val items: List<MenuHarian>,
    private val onEdit: (MenuHarian) -> Unit,
    private val onDelete: (MenuHarian) -> Unit
) : RecyclerView.Adapter<MenuHarianAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
        val tvMenu: TextView = view.findViewById(R.id.tvMenu)
        val tvKalori: TextView = view.findViewById(R.id.tvKalori)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_harian, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvTanggal.text = item.tanggal
        holder.tvMenu.text = item.menu
        holder.tvKalori.text = "Kalori: ${item.kalori}"
        holder.btnEdit.setOnClickListener { onEdit(item) }
        holder.btnDelete.setOnClickListener { onDelete(item) }
    }

    override fun getItemCount() = items.size
}
