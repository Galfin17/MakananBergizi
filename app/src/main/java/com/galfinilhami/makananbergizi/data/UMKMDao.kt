package com.galfinilhami.makananbergizi.data

import androidx.room.*

@Dao
interface UMKMDao {
    @Query("SELECT * FROM umkm ORDER BY nama ASC")
    fun getAll(): List<UMKM>

    @Insert
    fun insert(umkm: UMKM)

    @Update
    fun update(umkm: UMKM)

    @Delete
    fun delete(umkm: UMKM)
}
