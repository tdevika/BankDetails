package com.example.bankdetails.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bankdetails.databinding.FragmentBanksListBinding

class BanksListFragment : Fragment() {
    lateinit var binding: FragmentBanksListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBanksListBinding.inflate(inflater, container, false)
        return binding.root
    }
}