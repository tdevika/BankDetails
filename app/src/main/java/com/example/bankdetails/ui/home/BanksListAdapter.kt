package com.example.bankdetails.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bankdetails.data.model.BanksListData
import com.example.bankdetails.databinding.ItemBanksListBinding
import javax.inject.Inject

class BanksListAdapter @Inject constructor() :
    ListAdapter<BanksListData, BanksListAdapter.BanksListViewHolder>(diffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanksListViewHolder {
        val binding =
            ItemBanksListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BanksListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BanksListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BanksListViewHolder(private val binding: ItemBanksListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BanksListData) {
            binding.bankData = item
        }

    }
}

val diffUtils = object : DiffUtil.ItemCallback<BanksListData>() {
    override fun areItemsTheSame(oldItem: BanksListData, newItem: BanksListData): Boolean {
        return oldItem.BANK == newItem.BANK
    }

    override fun areContentsTheSame(oldItem: BanksListData, newItem: BanksListData): Boolean {
        return oldItem == newItem
    }

}