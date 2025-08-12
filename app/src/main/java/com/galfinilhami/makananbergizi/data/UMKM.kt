package com.galfinilhami.makananbergizi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "umkm")
data class UMKM(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val alamat: String,
    val kontak: String
)
