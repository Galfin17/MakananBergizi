package com.galfinilhami.makananbergizi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.galfinilhami.makananbergizi.data.UMKM

class UMKMAdapter(
    private val items: List<UMKM>,
    private val onEdit: (UMKM) -> Unit,
    private val onDelete: (UMKM) -> Unit
) : RecyclerView.Adapter<UMKMAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvAlamat: TextView = view.findViewById(R.id.tvAlamat)
        val tvKontak: TextView = view.findViewById(R.id.tvKontak)
        val btnEdit: Button = view.findViewById(R.id.btnEditUmkm)
        val btnDelete: Button = view.findViewById(R.id.btnDeleteUmkm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_umkm, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvNama.text = item.nama
        holder.tvAlamat.text = item.alamat
        holder.tvKontak.text = item.kontak
        holder.btnEdit.setOnClickListener { onEdit(item) }
        holder.btnDelete.setOnClickListener { onDelete(item) }
    }

    override fun getItemCount() = items.size
}
