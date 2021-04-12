package com.example.bankdetails.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankdetails.BanksApplication
import com.example.bankdetails.databinding.FragmentBanksListBinding
import com.example.bankdetails.utils.BanksListViewModelFactory
import javax.inject.Inject

class BanksListFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: BanksListViewModelFactory

    @Inject
    lateinit var banksListAdapter: BanksListAdapter

    lateinit var viewModel: BanksListViewModel
    lateinit var binding: FragmentBanksListBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BanksApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBanksListBinding.inflate(inflater, container, false)
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
        viewModel._banksList.observe(viewLifecycleOwner, Observer {
            banksListAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        binding.recycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = banksListAdapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(BanksListViewModel::class.java)
    }
}