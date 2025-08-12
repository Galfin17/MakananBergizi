package com.galfinilhami.makananbergizi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_harian")
data class MenuHarian(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tanggal: String,
    val menu: String,
    val kalori: Int
)
