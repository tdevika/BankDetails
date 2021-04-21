package com.example.bankdetails.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankdetails.R
import com.example.bankdetails.adapter.FavoritesAdapter
import com.example.bankdetails.databinding.FragmentBanksBinding
import com.example.bankdetails.utils.BankSelected
import com.example.bankdetails.viewmodel.BanksViewModel

class BanksFragment : Fragment(),BankSelected {
    lateinit var binding: FragmentBanksBinding

    private val banksViewModel: BanksViewModel by viewModels({ requireActivity() })

    private val favoritesAdapter: FavoritesAdapter by lazy {
        FavoritesAdapter(this)
    }

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
        setToolbar()
        setRecyclerView()
        setObserver()
    }

    private fun setObserver() {
        banksViewModel.favorites.observe(viewLifecycleOwner, Observer {
            favoritesAdapter.submitList(it)
            if (it.isNotEmpty()) {
                binding.emptyView.visibility = View.GONE
            } else {
                binding.emptyView.visibility = View.VISIBLE
            }
        })
    }

    private fun setRecyclerView() {
        binding.recycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = favoritesAdapter

            val itemSwipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val builder = AlertDialog.Builder(requireActivity())
                    builder.setTitle("Delete Bank")
                    builder.setMessage("Are you sure want to delete bank?")
                    builder.setPositiveButton("Confirm") { _, _ ->
                        val position = viewHolder.absoluteAdapterPosition
                        if(banksViewModel.isValidPosition(position)) {
                            val ifsc = banksViewModel.getIfscCode(position)
                            ifsc?.let {
                                banksViewModel.deleteFavorite(ifsc)
                            }
                        }
                    }
                    builder.setNegativeButton("Cancel") { _, _ ->
                        val position = viewHolder.absoluteAdapterPosition
                        favoritesAdapter.notifyDataSetChanged()
                    }
                    builder.show()
                }
            }
            val swap = ItemTouchHelper(itemSwipe)
            swap.attachToRecyclerView(this)
        }
    }

    private fun setToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search_banks) {
            binding.root.findNavController()
                .navigate(BanksFragmentDirections.navigateToSearchFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBankSelected(ifscCode: String) {
        findNavController().navigate(BanksFragmentDirections.actionBanksFragmentToBankDetailsFragment(ifscCode))
    }
}