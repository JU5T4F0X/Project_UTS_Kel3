package com.example.aplikasiuts.data.repository

import com.example.aplikasiuts.data.dao.GuestDao084
import com.example.aplikasiuts.data.model.Guest070
import com.example.aplikasiuts.util.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GuestRepository084(private val guestDao084: GuestDao084) {

    suspend fun insertGuest084(guest070: Guest070): Long = withContext(Dispatchers.IO) {
        val trimmedGuest084 = guest070.copy(
            nama070 = guest070.nama070.trim(),
            alamat070 = guest070.alamat070.trim(),
            keperluan070 = guest070.keperluan070.trim()
        )
        guestDao084.insertGuest084(trimmedGuest084)
    }

    suspend fun getAllGuests084(userId070: Int): List<Guest070> = withContext(Dispatchers.IO) {
        guestDao084.getAllGuests084(userId070)
    }

    suspend fun getRecentGuests084(userId070: Int, limit084: Int = 3): List<Guest070> = withContext(Dispatchers.IO) {
        guestDao084.getRecentGuests084(userId070, limit084)
    }

    suspend fun getGuestById084(id070: Int, userId070: Int): Guest070? = withContext(Dispatchers.IO) {
        guestDao084.getGuestById084(id070, userId070)
    }

    suspend fun searchGuests084(userId070: Int, keyword084: String): List<Guest070> = withContext(Dispatchers.IO) {
        val trimmed084 = keyword084.trim()
        if (trimmed084.isEmpty()) {
            guestDao084.getAllGuests084(userId070)
        } else {
            guestDao084.searchGuests084(userId070, trimmed084)
        }
    }

    suspend fun countTodayGuests084(userId070: Int): Int = withContext(Dispatchers.IO) {
        val start084 = DateUtils.getStartOfDay()
        val end084 = DateUtils.getEndOfDay()
        guestDao084.countTodayGuests084(userId070, start084, end084)
    }

    suspend fun updateGuest084(guest070: Guest070): Int = withContext(Dispatchers.IO) {
        val trimmedGuest084 = guest070.copy(
            nama070 = guest070.nama070.trim(),
            alamat070 = guest070.alamat070.trim(),
            keperluan070 = guest070.keperluan070.trim()
        )
        guestDao084.updateGuest084(trimmedGuest084)
    }

    suspend fun deleteGuest084(guest070: Guest070): Int = withContext(Dispatchers.IO) {
        guestDao084.deleteGuest084(guest070)
    }

    suspend fun deleteGuestById084(id070: Int, userId070: Int): Int = withContext(Dispatchers.IO) {
        guestDao084.deleteGuestById084(id070, userId070)
    }
}
