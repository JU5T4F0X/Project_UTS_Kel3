package com.example.aplikasiuts.ui.register

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aplikasiuts.data.database.AppDatabase070
import com.example.aplikasiuts.data.model.User203
import com.example.aplikasiuts.data.repository.AuthRepository084
import com.example.aplikasiuts.databinding.ActivityRegister110Binding
import kotlinx.coroutines.launch

class RegisterActivity110 : AppCompatActivity() {

    private lateinit var binding110: ActivityRegister110Binding
    private lateinit var authRepository084: AuthRepository084

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding110 = ActivityRegister110Binding.inflate(layoutInflater)
        setContentView(binding110.root)

        val database070 = AppDatabase070.getInstance070(this)
        authRepository084 = AuthRepository084(database070.userDao084())

        setupListeners110()
    }

    private fun setupListeners110() {
        binding110.btnRegister.setOnClickListener {
            handleRegister110()
        }

        binding110.tvGoToLogin.setOnClickListener {
            finish()
        }
    }

    private fun handleRegister110() {
        val username110 = binding110.etRegisterUsername.text?.toString()?.trim().orEmpty()
        val password110 = binding110.etRegisterPassword.text?.toString()?.trim().orEmpty()

        binding110.tilRegisterUsername.error = null
        binding110.tilRegisterPassword.error = null

        // Guard clauses (Clean Code)
        if (username110.isEmpty()) {
            binding110.tilRegisterUsername.error = "Username wajib diisi"
            return
        }

        if (password110.isEmpty()) {
            binding110.tilRegisterPassword.error = "Password wajib diisi"
            return
        }

        // US-01: Password minimal 6 karakter
        if (password110.length < MIN_PASSWORD_LENGTH_110) {
            binding110.tilRegisterPassword.error = "Password minimal 6 karakter"
            return
        }

        lifecycleScope.launch {
            // US-01: Cek keunikan username
            val exists110 = authRepository084.checkUsernameExists084(username110)
            if (exists110) {
                binding110.tilRegisterUsername.error = "Username sudah digunakan"
                return@launch
            }

            val newUser203 = User203(
                username203 = username110,
                password203 = password110
            )

            val rowId110 = authRepository084.registerUser084(newUser203)
            if (rowId110 > 0) {
                Toast.makeText(
                    this@RegisterActivity110,
                    "Registrasi berhasil! Silakan login.",
                    Toast.LENGTH_SHORT
                ).show()
                finish() // Kembali ke halaman Login
            } else {
                Toast.makeText(
                    this@RegisterActivity110,
                    "Gagal membuat akun, silakan coba lagi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH_110 = 6
    }
}
