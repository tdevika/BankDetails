package com.example.bankdetails.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankdetails.BanksApplication
import com.example.bankdetails.databinding.FragmentBanksBinding
import com.example.bankdetails.utils.BanksViewModelFactory
import javax.inject.Inject

class BanksFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: BanksViewModelFactory

    @Inject
    lateinit var banksListAdapter: BanksListAdapter

    lateinit var viewModel: BanksViewModel
    lateinit var binding: FragmentBanksBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BanksApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBanksBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        setObserver()
    }

    private fun setObserver() {
        viewModel.banks.observe(viewLifecycleOwner, {
            banksListAdapter.submitList(it)
        })

        banksListAdapter.setListener { ifscCode: String ->
            findNavController().navigate(
                BanksFragmentDirections.actionBanksListFragmentToBankDetailsFragment(
                    ifscCode
                )
            )
        }
    }

    private fun setupRecyclerView() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = banksListAdapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(BanksViewModel::class.java)
    }
}