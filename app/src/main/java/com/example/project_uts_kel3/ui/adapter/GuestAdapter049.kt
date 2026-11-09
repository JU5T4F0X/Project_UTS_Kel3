package com.example.aplikasiuts.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiuts.data.model.Guest070
import com.example.aplikasiuts.databinding.ItemGuest049Binding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GuestAdapter049(
    private val onItemClick049: (Guest070) -> Unit
) : ListAdapter<Guest070, GuestAdapter049.GuestViewHolder049>(
    GuestDiffCallback049()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder049 {
        val binding049 = ItemGuest049Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GuestViewHolder049(binding049, onItemClick049)
    }

    override fun onBindViewHolder(holder: GuestViewHolder049, position: Int) {
        holder.bind049(getItem(position))
    }

    class GuestViewHolder049(
        private val binding049: ItemGuest049Binding,
        private val onItemClick049: (Guest070) -> Unit
    ) : RecyclerView.ViewHolder(binding049.root) {

        private val dateFormat049 = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

        fun bind049(guest070: Guest070) {
            binding049.tvGuestName.text = guest070.nama070
            binding049.tvGuestAddress.text = guest070.alamat070
            binding049.tvGuestPurpose.text = guest070.keperluan070
            binding049.tvGuestTime.text = dateFormat049.format(Date(guest070.timestamp070))

            binding049.root.setOnClickListener {
                onItemClick049(guest070)
            }
        }
    }

    private class GuestDiffCallback049 : DiffUtil.ItemCallback<Guest070>() {
        override fun areItemsTheSame(oldItem049: Guest070, newItem049: Guest070): Boolean {
            return oldItem049.id070 == newItem049.id070
        }

        override fun areContentsTheSame(oldItem049: Guest070, newItem049: Guest070): Boolean {
            return oldItem049 == newItem049
        }
    }
}
