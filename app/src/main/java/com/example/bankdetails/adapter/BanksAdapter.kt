package com.example.bankdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bankdetails.model.BankInfo
import com.example.bankdetails.databinding.ItemBanksBinding
import com.example.bankdetails.fragment.BanksFragmentDirections

class BanksAdapter :
    ListAdapter<BankInfo, BanksAdapter.BanksListViewHolder>(diffUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanksListViewHolder {
        val binding =
            ItemBanksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BanksListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BanksListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BanksListViewHolder(private val binding: ItemBanksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BankInfo) {
            binding.bankData = item
            itemView.setOnClickListener {
                binding.root.findNavController().navigate( BanksFragmentDirections.navigateToBankDetailsFragment(
                    item.ifsc))
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