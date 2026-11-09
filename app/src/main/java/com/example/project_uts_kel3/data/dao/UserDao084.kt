package com.example.aplikasiuts.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aplikasiuts.data.model.User203

@Dao
interface UserDao084 {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser084(user203: User203): Long

    @Query("SELECT COUNT(*) FROM table_user_203 WHERE username_203 = :username203")
    suspend fun checkUsername084(username203: String): Int

    @Query("SELECT * FROM table_user_203 WHERE username_203 = :username203 AND password_203 = :password203 LIMIT 1")
    suspend fun login084(username203: String, password203: String): User203?

    @Query("SELECT * FROM table_user_203 WHERE id203 = :id203 LIMIT 1")
    suspend fun getUserById084(id203: Int): User203?
}
