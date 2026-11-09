package com.example.aplikasiuts.data.repository

import com.example.aplikasiuts.data.dao.UserDao084
import com.example.aplikasiuts.data.model.User203
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository084(private val userDao084: UserDao084) {

    suspend fun checkUsernameExists084(username203: String): Boolean = withContext(Dispatchers.IO) {
        userDao084.checkUsername084(username203.trim()) > 0
    }

    suspend fun registerUser084(user203: User203): Long = withContext(Dispatchers.IO) {
        val trimmedUser084 = user203.copy(
            username203 = user203.username203.trim(),
            password203 = user203.password203.trim()
        )
        userDao084.insertUser084(trimmedUser084)
    }

    suspend fun login084(username203: String, password203: String): User203? = withContext(Dispatchers.IO) {
        userDao084.login084(username203.trim(), password203.trim())
    }

    suspend fun getUserById084(userId203: Int): User203? = withContext(Dispatchers.IO) {
        userDao084.getUserById084(userId203)
    }
}
