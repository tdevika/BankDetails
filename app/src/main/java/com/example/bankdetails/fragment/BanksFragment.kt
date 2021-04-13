package com.example.bankdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankdetails.adapter.BanksAdapter
import com.example.bankdetails.databinding.FragmentBanksBinding
import com.example.bankdetails.viewmodel.HomeViewModel

class BanksFragment : Fragment() {

    private val banksAdapter: BanksAdapter by lazy {
        BanksAdapter()
    }

    private val homeViewModel: HomeViewModel by viewModels(ownerProducer = { requireParentFragment() })
    lateinit var binding: FragmentBanksBinding

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
        setupRecyclerView()
        setObserver()
    }

    private fun setObserver() {
        homeViewModel.banks.observe(viewLifecycleOwner, {
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
}