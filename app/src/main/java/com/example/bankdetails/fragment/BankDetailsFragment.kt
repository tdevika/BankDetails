package com.example.bankdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.bankdetails.databinding.FragmentBankDetailsBinding
import com.example.bankdetails.viewmodel.BanksViewModel

class BankDetailsFragment : Fragment() {

    private val banksViewModel: BanksViewModel by viewModels({requireActivity()})

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
        setToolbar()
        val ifscCode = BankDetailsFragmentArgs.fromBundle(requireArguments()).ifscCode
        banksViewModel.getBankDetails(ifscCode)
        binding.favorite.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                banksViewModel.setFavorite()
            }else{
                banksViewModel.deleteFavorite(ifscCode)
            }
        }
    }

    private fun setToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigate(BankDetailsFragmentDirections.actionBankDetailsFragmentToBanksFragment())
        }
    }
}