package com.galfinilhami.makananbergizi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuHarian::class, UMKM::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuHarianDao(): MenuHarianDao
    abstract fun umkmDao(): UMKMDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "app_db"
                ).allowMainThreadQueries().build().also { instance = it }
            }
    }
}
