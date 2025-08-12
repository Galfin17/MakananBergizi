package com.galfinilhami.makananbergizi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SekolahDashboardAdapter(private val items: List<SekolahDashboard>) : RecyclerView.Adapter<SekolahDashboardAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNamaSekolah)
        val tvNpsn: TextView = view.findViewById(R.id.tvNpsn)
        val tvJenjang: TextView = view.findViewById(R.id.tvJenjang)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvStatusMBG: TextView = view.findViewById(R.id.tvStatusMBG)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sekolah_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvNama.text = item.nama
        holder.tvNpsn.text = "NPSN: ${item.npsn}"
        holder.tvJenjang.text = "Jenjang: ${item.jenjang}"
        holder.tvStatus.text = "Status: ${item.status}"
        holder.tvStatusMBG.text = "Status MBG: ${item.statusMBG}"
    }

    override fun getItemCount() = items.size
}
