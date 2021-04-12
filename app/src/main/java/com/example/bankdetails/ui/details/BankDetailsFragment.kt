package com.example.bankdetails.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bankdetails.databinding.FragmentBankDetailsBinding

class BankDetailsFragment: Fragment(){
    lateinit var binding: FragmentBankDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBankDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}