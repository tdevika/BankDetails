package com.example.bankdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bankdetails.databinding.ItemBanksBinding
import com.example.bankdetails.model.FavoriteBank
import com.example.bankdetails.utils.BankSelected


class FavoritesAdapter(val bankSelectedDelegate: BankSelected) :
    ListAdapter<FavoriteBank, FavoritesAdapter.FavouriteViewHolder>(favoriteDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding = ItemBanksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getBankAt(position: Int): String {
        return getItem(position).ifsc
    }

    inner class FavouriteViewHolder(val binding: ItemBanksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteBank: FavoriteBank) {
            binding.bankData = favoriteBank
            itemView.setOnClickListener {
                bankSelectedDelegate.onBankSelected(favoriteBank.ifsc)
            }
        }
    }
}

val favoriteDiffUtils = object : DiffUtil.ItemCallback<FavoriteBank>() {
    override fun areItemsTheSame(oldItem: FavoriteBank, newItem: FavoriteBank): Boolean {
        return oldItem.ifsc == newItem.ifsc
    }

    override fun areContentsTheSame(oldItem: FavoriteBank, newItem: FavoriteBank): Boolean {
        return oldItem == newItem
    }
}
