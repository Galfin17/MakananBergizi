package com.galfinilhami.makananbergizi

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.galfinilhami.makananbergizi.data.AppDatabase
import com.galfinilhami.makananbergizi.data.UMKM

class UMKMFragment : Fragment() {
    private lateinit var db: AppDatabase
    private lateinit var adapter: UMKMAdapter
    private var data: MutableList<UMKM> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_umkm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getDatabase(requireContext())
        val rv = view.findViewById<RecyclerView>(R.id.rvUmkm)
        adapter = UMKMAdapter(data,
            onEdit = { showForm(it) },
            onDelete = { hapusUmkm(it) }
        )
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        view.findViewById<View>(R.id.btnTambahUmkm).setOnClickListener {
            showForm(null)
        }
        loadData()
    }

    private fun loadData() {
        data.clear()
        data.addAll(db.umkmDao().getAll())
        adapter.notifyDataSetChanged()
    }

    private fun showForm(umkm: UMKM?) {
        val etNama = EditText(requireContext())
        etNama.hint = "Nama UMKM"
        val etAlamat = EditText(requireContext())
        etAlamat.hint = "Alamat"
        val etKontak = EditText(requireContext())
        etKontak.hint = "Kontak"

        val layout = android.widget.LinearLayout(requireContext())
        layout.orientation = android.widget.LinearLayout.VERTICAL
        layout.addView(etNama)
        layout.addView(etAlamat)
        layout.addView(etKontak)

        if (umkm != null) {
            etNama.setText(umkm.nama)
            etAlamat.setText(umkm.alamat)
            etKontak.setText(umkm.kontak)
        }

        AlertDialog.Builder(requireContext())
            .setTitle(if (umkm == null) "Tambah UMKM" else "Edit UMKM")
            .setView(layout)
            .setPositiveButton("Simpan") { _, _ ->
                val nama = etNama.text.toString()
                val alamat = etAlamat.text.toString()
                val kontak = etKontak.text.toString()
                if (nama.isBlank() || alamat.isBlank() || kontak.isBlank()) {
                    Toast.makeText(requireContext(), "Isi semua data", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                if (umkm == null) {
                    db.umkmDao().insert(UMKM(nama = nama, alamat = alamat, kontak = kontak))
                } else {
                    db.umkmDao().update(umkm.copy(nama = nama, alamat = alamat, kontak = kontak))
                }
                loadData()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun hapusUmkm(umkm: UMKM) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus UMKM")
            .setMessage("Yakin hapus UMKM ini?")
            .setPositiveButton("Hapus") { _, _ ->
                db.umkmDao().delete(umkm)
                loadData()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
