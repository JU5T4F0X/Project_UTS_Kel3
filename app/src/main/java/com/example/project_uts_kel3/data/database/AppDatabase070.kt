package com.example.aplikasiuts.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aplikasiuts.data.dao.GuestDao084
import com.example.aplikasiuts.data.dao.UserDao084
import com.example.aplikasiuts.data.model.Guest070
import com.example.aplikasiuts.data.model.User203

@Database(
    entities = [User203::class, Guest070::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase070 : RoomDatabase() {

    abstract fun userDao084(): UserDao084
    abstract fun guestDao084(): GuestDao084

    companion object {
        @Volatile
        private var dbInstance070: AppDatabase070? = null

        fun getInstance070(context070: Context): AppDatabase070 {
            return dbInstance070 ?: synchronized(this) {
                dbInstance070 ?: Room.databaseBuilder(
                    context070.applicationContext,
                    AppDatabase070::class.java,
                    "buku_tamu_database_070"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { dbInstance070 = it }
            }
        }
    }
}
