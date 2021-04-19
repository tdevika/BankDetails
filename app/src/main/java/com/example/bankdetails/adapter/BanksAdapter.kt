package com.example.bankdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bankdetails.databinding.ItemIfscsBinding
import com.example.bankdetails.fragment.BankSelected

class BanksAdapter(private val bankSelectedDelegate: BankSelected) :
    ListAdapter<String, BanksAdapter.BanksListViewHolder>(diffUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanksListViewHolder {
        val binding =
            ItemIfscsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BanksListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BanksListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BanksListViewHolder(private val binding: ItemIfscsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.ifscData = item
            itemView.setOnClickListener {
                bankSelectedDelegate.onBankSelected(item)
            }
        }
    }
}

val diffUtils = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}