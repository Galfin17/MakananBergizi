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
import com.galfinilhami.makananbergizi.data.MenuHarian

class MenuHarianFragment : Fragment() {
    private lateinit var db: AppDatabase
    private lateinit var adapter: MenuHarianAdapter
    private var data: MutableList<MenuHarian> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_harian, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getDatabase(requireContext())
        val rv = view.findViewById<RecyclerView>(R.id.rvMenuHarian)
        adapter = MenuHarianAdapter(data,
            onEdit = { showForm(it) },
            onDelete = { hapusMenu(it) }
        )
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        view.findViewById<View>(R.id.btnTambahMenu).setOnClickListener {
            showForm(null)
        }
        loadData()
    }

    private fun loadData() {
        data.clear()
        data.addAll(db.menuHarianDao().getAll())
        adapter.notifyDataSetChanged()
    }

    private fun showForm(menu: MenuHarian?) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(android.R.layout.simple_list_item_2, null)
        val etTanggal = EditText(requireContext())
        etTanggal.hint = "Tanggal (yyyy-mm-dd)"
        val etMenu = EditText(requireContext())
        etMenu.hint = "Menu"
        val etKalori = EditText(requireContext())
        etKalori.hint = "Kalori"
        etKalori.inputType = android.text.InputType.TYPE_CLASS_NUMBER

        val layout = android.widget.LinearLayout(requireContext())
        layout.orientation = android.widget.LinearLayout.VERTICAL
        layout.addView(etTanggal)
        layout.addView(etMenu)
        layout.addView(etKalori)

        if (menu != null) {
            etTanggal.setText(menu.tanggal)
            etMenu.setText(menu.menu)
            etKalori.setText(menu.kalori.toString())
        }

        AlertDialog.Builder(requireContext())
            .setTitle(if (menu == null) "Tambah Menu" else "Edit Menu")
            .setView(layout)
            .setPositiveButton("Simpan") { _, _ ->
                val tanggal = etTanggal.text.toString()
                val menuText = etMenu.text.toString()
                val kalori = etKalori.text.toString().toIntOrNull() ?: 0
                if (tanggal.isBlank() || menuText.isBlank()) {
                    Toast.makeText(requireContext(), "Isi semua data", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                if (menu == null) {
                    db.menuHarianDao().insert(MenuHarian(tanggal = tanggal, menu = menuText, kalori = kalori))
                } else {
                    db.menuHarianDao().update(menu.copy(tanggal = tanggal, menu = menuText, kalori = kalori))
                }
                loadData()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun hapusMenu(menu: MenuHarian) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Menu")
            .setMessage("Yakin hapus menu ini?")
            .setPositiveButton("Hapus") { _, _ ->
                db.menuHarianDao().delete(menu)
                loadData()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
