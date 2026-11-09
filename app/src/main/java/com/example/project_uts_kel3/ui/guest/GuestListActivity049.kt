package com.example.aplikasiuts.ui.guest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasiuts.data.database.AppDatabase070
import com.example.aplikasiuts.data.model.Guest070
import com.example.aplikasiuts.data.pref.SessionPrefs203
import com.example.aplikasiuts.data.repository.GuestRepository084
import com.example.aplikasiuts.databinding.ActivityGuestList049Binding
import com.example.aplikasiuts.ui.adapter.GuestAdapter049
import kotlinx.coroutines.launch

class GuestListActivity049 : AppCompatActivity() {

    private lateinit var binding049: ActivityGuestList049Binding
    private lateinit var sessionPrefs203: SessionPrefs203
    private lateinit var guestRepository084: GuestRepository084
    private lateinit var guestAdapter049: GuestAdapter049

    private var currentUserId070: Int = SessionPrefs203.DEFAULT_USER_ID_203
    private var allGuestsList049: List<Guest070> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding049 = ActivityGuestList049Binding.inflate(layoutInflater)
        setContentView(binding049.root)

        sessionPrefs203 = SessionPrefs203(this)
        currentUserId070 = sessionPrefs203.getUserId203()

        if (currentUserId070 == SessionPrefs203.DEFAULT_USER_ID_203) {
            finish()
            return
        }

        val database070 = AppDatabase070.getInstance070(this)
        guestRepository084 = GuestRepository084(database070.guestDao084())

        setupToolbar049()
        setupRecyclerView049()
        setupSearchView049()
        setupListeners049()
    }

    override fun onResume() {
        super.onResume()
        loadGuests049()
    }

    private fun setupToolbar049() {
        binding049.toolbarGuestList.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView049() {
        guestAdapter049 = GuestAdapter049 { guest070 ->
            val intent049 = Intent(this, GuestFormActivity070::class.java).apply {
                putExtra(GuestFormActivity070.EXTRA_GUEST_ID_070, guest070.id070)
            }
            startActivity(intent049)
        }

        binding049.recyclerViewGuests.apply {
            layoutManager = LinearLayoutManager(this@GuestListActivity049)
            adapter = guestAdapter049
        }
    }

    private fun setupSearchView049() {
        binding049.searchViewGuest.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterGuests049(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterGuests049(newText.orEmpty())
                return true
            }
        })
    }

    private fun setupListeners049() {
        binding049.fabAddGuest.setOnClickListener {
            val intent049 = Intent(this, GuestFormActivity070::class.java)
            startActivity(intent049)
        }
    }

    private fun loadGuests049() {
        lifecycleScope.launch {
            try {
                allGuestsList049 = guestRepository084.getAllGuests084(currentUserId070)
                updateListUI049(allGuestsList049)
            } catch (e: Exception) {
                allGuestsList049 = emptyList()
                updateListUI049(allGuestsList049)
            }
        }
    }

    private fun filterGuests049(query: String) {
        lifecycleScope.launch {
            if (query.isBlank()) {
                updateListUI049(allGuestsList049)
            } else {
                try {
                    val searchResult049 = guestRepository084.searchGuests084(currentUserId070, "%$query%")
                    updateListUI049(searchResult049)
                } catch (e: Exception) {
                    val filtered049 = allGuestsList049.filter {
                        it.nama070.contains(query, ignoreCase = true) ||
                                it.keperluan070.contains(query, ignoreCase = true) ||
                                it.alamat070.contains(query, ignoreCase = true)
                    }
                    updateListUI049(filtered049)
                }
            }
        }
    }

    private fun updateListUI049(list049: List<Guest070>) {
        guestAdapter049.submitList(list049)
        if (list049.isEmpty()) {
            binding049.recyclerViewGuests.visibility = View.GONE
            binding049.layoutEmptyState.visibility = View.VISIBLE
        } else {
            binding049.recyclerViewGuests.visibility = View.VISIBLE
            binding049.layoutEmptyState.visibility = View.GONE
        }
    }
}
