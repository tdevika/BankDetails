package com.example.bankdetails.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankdetails.adapter.BanksAdapter
import com.example.bankdetails.databinding.FragmentBanksBinding
import com.example.bankdetails.viewmodel.BanksViewModel

class BankFragment : Fragment(),BankSelected {

    private val banksAdapter: BanksAdapter by lazy {
        BanksAdapter(this)
    }

    private val banksViewModel: BanksViewModel by viewModels(ownerProducer = { requireParentFragment() })
    lateinit var binding: FragmentBanksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBanksBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setObserver()
    }

    private fun setObserver() {
        banksViewModel.banks.observe(viewLifecycleOwner, {
            banksAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = banksAdapter
        }
    }

    override fun onBankSelected(ifscCode: String) {
        binding.root.findNavController().navigate( BankFragmentDirections.navigateToBankDetailsFragment(
            ifscCode))
    }
}

interface BankSelected{
    fun onBankSelected(ifscCode:String)
}