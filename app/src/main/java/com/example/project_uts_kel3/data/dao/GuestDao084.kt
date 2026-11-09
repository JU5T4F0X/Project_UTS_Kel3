package com.example.aplikasiuts.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.aplikasiuts.data.model.Guest070
import kotlinx.coroutines.flow.Flow

@Dao
interface GuestDao084 {

    @Insert
    suspend fun insertGuest084(guest070: Guest070): Long

    @Query("SELECT * FROM table_guest_070 WHERE user_id070 = :userId070 ORDER BY timestamp_070 DESC")
    fun getAllGuestsFlow084(userId070: Int): Flow<List<Guest070>>

    @Query("SELECT * FROM table_guest_070 WHERE user_id070 = :userId070 ORDER BY timestamp_070 DESC")
    suspend fun getAllGuests084(userId070: Int): List<Guest070>

    @Query("SELECT * FROM table_guest_070 WHERE user_id070 = :userId070 ORDER BY timestamp_070 DESC LIMIT :limit084")
    suspend fun getRecentGuests084(userId070: Int, limit084: Int = 3): List<Guest070>

    @Query("SELECT * FROM table_guest_070 WHERE id070 = :id070 AND user_id070 = :userId070 LIMIT 1")
    suspend fun getGuestById084(id070: Int, userId070: Int): Guest070?

    @Query("SELECT * FROM table_guest_070 WHERE user_id070 = :userId070 AND nama_070 LIKE '%' || :keyword084 || '%' ORDER BY timestamp_070 DESC")
    suspend fun searchGuests084(userId070: Int, keyword084: String): List<Guest070>

    @Query("SELECT COUNT(*) FROM table_guest_070 WHERE user_id070 = :userId070 AND timestamp_070 BETWEEN :startOfDay084 AND :endOfDay084")
    suspend fun countTodayGuests084(userId070: Int, startOfDay084: Long, endOfDay084: Long): Int

    @Update
    suspend fun updateGuest084(guest070: Guest070): Int

    @Delete
    suspend fun deleteGuest084(guest070: Guest070): Int

    @Query("DELETE FROM table_guest_070 WHERE id070 = :id070 AND user_id070 = :userId070")
    suspend fun deleteGuestById084(id070: Int, userId070: Int): Int
}
