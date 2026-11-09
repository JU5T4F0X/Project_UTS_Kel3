package com.example.aplikasiuts.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasiuts.data.database.AppDatabase070
import com.example.aplikasiuts.data.pref.SessionPrefs203
import com.example.aplikasiuts.data.repository.AuthRepository084
import com.example.aplikasiuts.data.repository.GuestRepository084
import com.example.aplikasiuts.databinding.ActivityDashboard205Binding
import com.example.aplikasiuts.ui.adapter.GuestAdapter049
import com.example.aplikasiuts.ui.guest.GuestFormActivity070
import com.example.aplikasiuts.ui.guest.GuestListActivity049
import com.example.aplikasiuts.ui.login.LoginActivity203
import kotlinx.coroutines.launch

class DashboardActivity205 : AppCompatActivity() {

    private lateinit var binding205: ActivityDashboard205Binding
    private lateinit var sessionPrefs203: SessionPrefs203
    private lateinit var authRepository084: AuthRepository084
    private lateinit var guestRepository084: GuestRepository084
    private lateinit var recentGuestAdapter049: GuestAdapter049

    private var currentUserId205: Int = SessionPrefs203.DEFAULT_USER_ID_203

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding205 = ActivityDashboard205Binding.inflate(layoutInflater)
        setContentView(binding205.root)

        sessionPrefs203 = SessionPrefs203(this)
        currentUserId205 = sessionPrefs203.getUserId203()

        // Jika sesi tidak valid, kembali ke Login
        if (currentUserId205 == SessionPrefs203.DEFAULT_USER_ID_203) {
            redirectToLogin205()
            return
        }

        val database070 = AppDatabase070.getInstance070(this)
        authRepository084 = AuthRepository084(database070.userDao084())
        guestRepository084 = GuestRepository084(database070.guestDao084())

        loadUserData205()
        setupRecentGuestsRecyclerView205()
        setupListeners205()
    }

    override fun onResume() {
        super.onResume()
        // US-04: Angka rekap otomatis terbarui setelah tambah/hapus tamu
        loadTodayGuestCount205()
        loadRecentGuests205()
    }

    private fun setupRecentGuestsRecyclerView205() {
        recentGuestAdapter049 = GuestAdapter049 { selectedGuest070 ->
            val intent205 = Intent(this, GuestFormActivity070::class.java).apply {
                putExtra(GuestFormActivity070.EXTRA_GUEST_ID_070, selectedGuest070.id070)
            }
            startActivity(intent205)
        }

        binding205.rvRecentGuests.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity205)
            adapter = recentGuestAdapter049
        }
    }

    private fun setupListeners205() {
        binding205.cardAddGuest.setOnClickListener {
            val intent205 = Intent(this, GuestFormActivity070::class.java)
            startActivity(intent205)
        }

        binding205.cardViewGuests.setOnClickListener {
            val intent205 = Intent(this, GuestListActivity049::class.java)
            startActivity(intent205)
        }

        binding205.tvViewAllRecent.setOnClickListener {
            val intent205 = Intent(this, GuestListActivity049::class.java)
            startActivity(intent205)
        }

        binding205.btnQuickLogout.setOnClickListener {
            showLogoutConfirmationDialog205()
        }
    }

    private fun loadUserData205() {
        lifecycleScope.launch {
            val user203 = authRepository084.getUserById084(currentUserId205)
            if (user203 != null) {
                binding205.tvWelcomeUser.text = user203.username203
            }
        }
    }

    private fun loadTodayGuestCount205() {
        lifecycleScope.launch {
            val count205 = guestRepository084.countTodayGuests084(currentUserId205)
            binding205.tvTodayCount.text = count205.toString()
        }
    }

    private fun loadRecentGuests205() {
        lifecycleScope.launch {
            val recentGuests070 = guestRepository084.getRecentGuests084(currentUserId205, 3)
            recentGuestAdapter049.submitList(recentGuests070)
            if (recentGuests070.isEmpty()) {
                binding205.rvRecentGuests.visibility = View.GONE
                binding205.tvEmptyRecent.visibility = View.VISIBLE
            } else {
                binding205.rvRecentGuests.visibility = View.VISIBLE
                binding205.tvEmptyRecent.visibility = View.GONE
            }
        }
    }

    // US-03: Logout dengan dialog konfirmasi dan bersihkan back stack
    private fun showLogoutConfirmationDialog205() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Keluar")
            .setMessage("Apakah Anda yakin ingin keluar dari akun ini?")
            .setPositiveButton("Ya, Keluar") { _, _ ->
                performLogout205()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun performLogout205() {
        sessionPrefs203.clearSession203()
        redirectToLogin205()
    }

    private fun redirectToLogin205() {
        val intent205 = Intent(this, LoginActivity203::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent205)
        finish()
    }
}
