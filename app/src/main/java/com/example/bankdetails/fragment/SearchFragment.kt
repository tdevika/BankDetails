package com.example.bankdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankdetails.adapter.BanksAdapter
import com.example.bankdetails.databinding.FragmentSearchBinding
import com.example.bankdetails.utils.BankSelected
import com.example.bankdetails.utils.hideSoftInput
import com.example.bankdetails.utils.isValidIFSCode
import com.example.bankdetails.utils.showSoftInput
import com.example.bankdetails.viewmodel.BanksViewModel
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragment : Fragment(), BankSelected {

    private val banksAdapter: BanksAdapter by lazy {
        BanksAdapter(this)
    }

    private val banksViewModel: BanksViewModel by viewModels({ requireActivity() })
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setToolbar()
        setObserver()
    }

    private fun setToolbar() {
        binding.toolbar.searchView.apply {
            searchView.onActionViewExpanded()
            searchView.queryHint = "Search"

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    hideSoftInput()
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    if (query.length > 2) {
                        banksViewModel.onSearchQueryChanged(query)
                    } else {
                        banksAdapter.submitList(emptyList<String>())
                    }
                    return true
                }
            })
            setOnQueryTextFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    view.findFocus().showSoftInput()
                }
            }
            requestFocus()
        }
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
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
        binding.root.findNavController().navigate(
            SearchFragmentDirections.navigateToBankDetailsFragment(ifscCode)
        )
    }

    override fun onPause() {
        hideSoftInput()
        super.onPause()
    }
}

