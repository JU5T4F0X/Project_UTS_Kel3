package com.example.aplikasiuts.data.pref

import android.content.Context
import android.content.SharedPreferences

class SessionPrefs203(context203: Context) {

    private val sharedPreferences203: SharedPreferences =
        context203.getSharedPreferences(PREF_NAME_203, Context.MODE_PRIVATE)

    fun saveUserId203(id203: Int) {
        sharedPreferences203.edit()
            .putInt(KEY_USER_ID_203, id203)
            .apply()
    }

    fun getUserId203(): Int {
        return sharedPreferences203.getInt(KEY_USER_ID_203, DEFAULT_USER_ID_203)
    }

    fun isLoggedIn203(): Boolean {
        return getUserId203() != DEFAULT_USER_ID_203
    }

    fun clearSession203() {
        sharedPreferences203.edit()
            .remove(KEY_USER_ID_203)
            .apply()
    }

    companion object {
        private const val PREF_NAME_203 = "buku_tamu_session_prefs_203"
        const val KEY_USER_ID_203 = "session_user_id_203"
        const val DEFAULT_USER_ID_203 = -1
    }
}
