package com.galfinilhami.makananbergizi.data

import androidx.room.*

@Dao
interface MenuHarianDao {
    @Query("SELECT * FROM menu_harian ORDER BY tanggal DESC")
    fun getAll(): List<MenuHarian>

    @Insert
    fun insert(menu: MenuHarian)

    @Update
    fun update(menu: MenuHarian)

    @Delete
    fun delete(menu: MenuHarian)
}
