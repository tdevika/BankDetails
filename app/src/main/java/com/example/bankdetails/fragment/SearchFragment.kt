package com.example.bankdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankdetails.adapter.BanksAdapter
import com.example.bankdetails.databinding.FragmentSearchBinding
import com.example.bankdetails.utils.BankSelected
import com.example.bankdetails.utils.BanksViewModelFactory
import com.example.bankdetails.utils.hideSoftInput
import com.example.bankdetails.utils.showSoftInput
import com.example.bankdetails.viewmodel.BanksViewModel
import kotlinx.android.synthetic.main.fragment_search.view.*
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance


class SearchFragment : Fragment(), BankSelected, DIAware {

    private val banksAdapter: BanksAdapter by lazy {
        BanksAdapter(this)
    }

    override val di by di()

    private val viewModeFactory: BanksViewModelFactory by instance("banksViewModelFactory")

    lateinit var banksViewModel: BanksViewModel

    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        banksViewModel = ViewModelProvider(this, viewModeFactory).get(BanksViewModel::class.java)
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
        banksViewModel.searchedBanks.observe(viewLifecycleOwner, {
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

