package com.example.bankdetails.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankdetails.BanksApplication
import com.example.bankdetails.databinding.FragmentBankDetailsBinding
import com.example.bankdetails.utils.BanksViewModelFactory
import javax.inject.Inject

class BankDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: BanksViewModelFactory

    lateinit var viewModel: BankDetailsViewModel
    lateinit var binding: FragmentBankDetailsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BanksApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBankDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        val ifscCode = BankDetailsFragmentArgs.fromBundle(requireArguments()).ifscCode
        viewModel.getBankDetails(ifscCode)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(BankDetailsViewModel::class.java)
        binding.viewModel = viewModel
    }
}