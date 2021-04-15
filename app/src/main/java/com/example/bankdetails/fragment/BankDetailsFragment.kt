package com.example.bankdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bankdetails.databinding.FragmentBankDetailsBinding
import com.example.bankdetails.viewmodel.BanksViewModel

class BankDetailsFragment : Fragment() {

    private val banksViewModel: BanksViewModel by viewModels(ownerProducer = { requireParentFragment() })

    lateinit var binding: FragmentBankDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBankDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = banksViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ifscCode = BankDetailsFragmentArgs.fromBundle(requireArguments()).ifscCode
        banksViewModel.getBankDetails(ifscCode)
    }
}