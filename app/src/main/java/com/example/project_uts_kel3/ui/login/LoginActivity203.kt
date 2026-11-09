package com.example.aplikasiuts.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aplikasiuts.data.database.AppDatabase070
import com.example.aplikasiuts.data.pref.SessionPrefs203
import com.example.aplikasiuts.data.repository.AuthRepository084
import com.example.aplikasiuts.databinding.ActivityLogin203Binding
import com.example.aplikasiuts.ui.dashboard.DashboardActivity205
import com.example.aplikasiuts.ui.register.RegisterActivity110
import kotlinx.coroutines.launch

class LoginActivity203 : AppCompatActivity() {

    private lateinit var binding203: ActivityLogin203Binding
    private lateinit var sessionPrefs203: SessionPrefs203
    private lateinit var authRepository084: AuthRepository084

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionPrefs203 = SessionPrefs203(this)

        // US-02: Jika sesi masih ada, user langsung ke Dashboard (skip Login)
        if (sessionPrefs203.isLoggedIn203()) {
            navigateToDashboard203()
            return
        }

        binding203 = ActivityLogin203Binding.inflate(layoutInflater)
        setContentView(binding203.root)

        val database070 = AppDatabase070.getInstance070(this)
        authRepository084 = AuthRepository084(database070.userDao084())

        setupListeners203()
    }

    private fun setupListeners203() {
        binding203.btnLogin.setOnClickListener {
            handleLogin203()
        }

        binding203.tvGoToRegister.setOnClickListener {
            val intent203 = Intent(this, RegisterActivity110::class.java)
            startActivity(intent203)
        }
    }

    private fun handleLogin203() {
        val username203 = binding203.etUsername.text?.toString()?.trim().orEmpty()
        val password203 = binding203.etPassword.text?.toString()?.trim().orEmpty()

        binding203.tilUsername.error = null
        binding203.tilPassword.error = null

        // Guard clauses untuk validasi input kosong
        if (username203.isEmpty()) {
            binding203.tilUsername.error = "Username wajib diisi"
            return
        }

        if (password203.isEmpty()) {
            binding203.tilPassword.error = "Password wajib diisi"
            return
        }

        lifecycleScope.launch {
            val user203 = authRepository084.login084(username203, password203)
            if (user203 != null) {
                // Simpan ID user ke SessionPrefs203
                sessionPrefs203.saveUserId203(user203.id203)
                Toast.makeText(this@LoginActivity203, "Login berhasil", Toast.LENGTH_SHORT).show()
                navigateToDashboard203()
            } else {
                // US-02: Tampilkan pesan tanpa memberi tahu bagian mana yang salah
                Toast.makeText(
                    this@LoginActivity203,
                    "Username atau password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun navigateToDashboard203() {
        val intent203 = Intent(this, DashboardActivity205::class.java)
        startActivity(intent203)
        finish()
    }
}
