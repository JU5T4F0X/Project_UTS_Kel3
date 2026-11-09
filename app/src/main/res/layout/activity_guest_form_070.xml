package com.example.aplikasiuts.ui.guest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aplikasiuts.data.database.AppDatabase070
import com.example.aplikasiuts.data.model.Guest070
import com.example.aplikasiuts.data.pref.SessionPrefs203
import com.example.aplikasiuts.data.repository.GuestRepository084
import com.example.aplikasiuts.databinding.ActivityGuestForm070Binding
import kotlinx.coroutines.launch

class GuestFormActivity070 : AppCompatActivity() {

    private lateinit var binding070: ActivityGuestForm070Binding
    private lateinit var sessionPrefs203: SessionPrefs203
    private lateinit var guestRepository084: GuestRepository084

    private var currentUserId070: Int = SessionPrefs203.DEFAULT_USER_ID_203
    private var guestIdToEdit070: Int = DEFAULT_NO_ID_070
    private var existingGuest070: Guest070? = null

    private val isEditMode070: Boolean
        get() = guestIdToEdit070 != DEFAULT_NO_ID_070

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding070 = ActivityGuestForm070Binding.inflate(layoutInflater)
        setContentView(binding070.root)

        sessionPrefs203 = SessionPrefs203(this)
        currentUserId070 = sessionPrefs203.getUserId203()

        // Cegah akses tanpa sesi valid
        if (currentUserId070 == SessionPrefs203.DEFAULT_USER_ID_203) {
            finish()
            return
        }

        val database070 = AppDatabase070.getInstance070(this)
        guestRepository084 = GuestRepository084(database070.guestDao084())

        guestIdToEdit070 = intent.getIntExtra(EXTRA_GUEST_ID_070, DEFAULT_NO_ID_070)

        setupUI070()
        setupListeners070()

        if (isEditMode070) {
            loadExistingGuestData070()
        }
    }

    private fun setupUI070() {
        binding070.toolbarForm.setNavigationOnClickListener {
            finish()
        }

        if (isEditMode070) {
            binding070.toolbarForm.title = "Edit Data Tamu"
            binding070.btnSaveGuest.text = "Simpan Perubahan"
            binding070.btnDeleteGuest.visibility = View.VISIBLE
        } else {
            binding070.toolbarForm.title = "Tambah Tamu Baru"
            binding070.btnSaveGuest.text = "Simpan Data Tamu"
            binding070.btnDeleteGuest.visibility = View.GONE
        }
    }

    private fun setupListeners070() {
        binding070.btnSaveGuest.setOnClickListener {
            handleSave070()
        }

        binding070.btnDeleteGuest.setOnClickListener {
            showDeleteConfirmationDialog070()
        }
    }

    // US-09: Isolasi data - hanya memuat jika id070 dan user_id070 cocok
    private fun loadExistingGuestData070() {
        lifecycleScope.launch {
            val guest070 = guestRepository084.getGuestById084(guestIdToEdit070, currentUserId070)
            if (guest070 != null) {
                existingGuest070 = guest070
                binding070.etGuestName.setText(guest070.nama070)
                binding070.etGuestAddress.setText(guest070.alamat070)
                binding070.etGuestPurpose.setText(guest070.keperluan070)
            } else {
                Toast.makeText(
                    this@GuestFormActivity070,
                    "Data tamu tidak ditemukan atau bukan milik akun ini",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun handleSave070() {
        val name070 = binding070.etGuestName.text?.toString()?.trim().orEmpty()
        val address070 = binding070.etGuestAddress.text?.toString()?.trim().orEmpty()
        val purpose070 = binding070.etGuestPurpose.text?.toString()?.trim().orEmpty()

        binding070.tilGuestName.error = null
        binding070.tilGuestAddress.error = null
        binding070.tilGuestPurpose.error = null

        // Guard clauses untuk validasi form (US-05 & US-07)
        if (name070.isEmpty()) {
            binding070.tilGuestName.error = "Nama wajib diisi"
            return
        }

        if (address070.isEmpty()) {
            binding070.tilGuestAddress.error = "Alamat wajib diisi"
            return
        }

        if (purpose070.isEmpty()) {
            binding070.tilGuestPurpose.error = "Keperluan wajib diisi"
            return
        }

        lifecycleScope.launch {
            if (isEditMode070) {
                val current070 = existingGuest070
                if (current070 != null) {
                    // US-07: user_id070 dan timestamp070 tidak berubah saat update
                    val updatedGuest070 = current070.copy(
                        nama070 = name070,
                        alamat070 = address070,
                        keperluan070 = purpose070
                    )
                    guestRepository084.updateGuest084(updatedGuest070)
                    Toast.makeText(
                        this@GuestFormActivity070,
                        "Data tamu berhasil diperbarui",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            } else {
                // US-05: timestamp070 otomatis dibuat, user_id070 dari sesi
                val newGuest070 = Guest070(
                    user_id070 = currentUserId070,
                    nama070 = name070,
                    alamat070 = address070,
                    keperluan070 = purpose070,
                    timestamp070 = System.currentTimeMillis()
                )
                guestRepository084.insertGuest084(newGuest070)
                Toast.makeText(
                    this@GuestFormActivity070,
                    "Data tamu berhasil disimpan",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    // US-08: Hapus tamu dengan dialog konfirmasi
    private fun showDeleteConfirmationDialog070() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Hapus")
            .setMessage("Apakah Anda yakin ingin menghapus data tamu ini?")
            .setPositiveButton("Ya, Hapus") { _, _ ->
                performDelete070()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun performDelete070() {
        val current070 = existingGuest070 ?: return
        lifecycleScope.launch {
            guestRepository084.deleteGuest084(current070)
            Toast.makeText(
                this@GuestFormActivity070,
                "Data tamu berhasil dihapus",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    companion object {
        const val EXTRA_GUEST_ID_070 = "extra_guest_id_070"
        private const val DEFAULT_NO_ID_070 = -1
    }
}
