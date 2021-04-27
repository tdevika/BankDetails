package com.example.bankdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bankdetails.databinding.FragmentBankDetailsBinding
import com.example.bankdetails.model.FavoriteBank
import com.example.bankdetails.utils.BanksViewModelFactory
import com.example.bankdetails.viewmodel.BanksViewModel
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class BankDetailsFragment : Fragment(), DIAware {

    override val di by di()

    private val viewModeFactory: BanksViewModelFactory by instance("banksViewModelFactory")

    lateinit var banksViewModel: BanksViewModel

    lateinit var binding: FragmentBankDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        banksViewModel = ViewModelProvider(this, viewModeFactory).get(BanksViewModel::class.java)
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
            if (isChecked) {
                banksViewModel.bankDetails.value?.let {
                    banksViewModel.setFavorite(
                        FavoriteBank(it.ifsc, it.bank)
                    )
                }
            } else {
                banksViewModel.deleteFavorite(ifscCode)
            }
        }
    }

    private fun setToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController()
                .navigate(BankDetailsFragmentDirections.actionBankDetailsFragmentToBanksFragment())
        }
    }
}