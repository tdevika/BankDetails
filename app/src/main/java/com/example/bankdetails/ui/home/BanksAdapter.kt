package com.example.bankdetails.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bankdetails.data.model.BankInfo
import com.example.bankdetails.databinding.ItemBanksBinding
import javax.inject.Inject

class BanksListAdapter @Inject constructor() :
    ListAdapter<BankInfo, BanksListAdapter.BanksListViewHolder>(diffUtils) {
    private lateinit var callback: (String) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanksListViewHolder {
        val binding =
            ItemBanksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BanksListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BanksListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setListener(ifsc: (String) -> Unit) {
        this.callback = ifsc
    }

    inner class BanksListViewHolder(private val binding: ItemBanksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BankInfo) {
            binding.bankData = item
            itemView.setOnClickListener {
                callback(item.ifsc)
            }
        }


    }
}

val diffUtils = object : DiffUtil.ItemCallback<BankInfo>() {
    override fun areItemsTheSame(oldItem: BankInfo, newItem: BankInfo): Boolean {
        return oldItem.bank == newItem.bank
    }

    override fun areContentsTheSame(oldItem: BankInfo, newItem: BankInfo): Boolean {
        return oldItem == newItem
    }

}