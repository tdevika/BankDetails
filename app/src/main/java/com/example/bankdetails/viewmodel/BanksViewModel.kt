package com.example.bankdetails.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.bankdetails.model.BankDetails
import com.example.bankdetails.model.FavoriteBank
import com.example.bankdetails.utils.DataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class BanksViewModel(
    private val dataSource: DataSource,
    application: Application,
    private val dispatcher: CoroutineDispatcher
) : AndroidViewModel(application) {


    val bankDetails = MutableLiveData<BankDetails>()
    val searchedBanks = MutableLiveData<List<String>>()
    var favorites = MutableLiveData<List<FavoriteBank>>()
        private set

    val isLoading = MutableLiveData<Boolean>()

    init {
        getFavoriteBanks()
    }

    fun getFavoriteBanks() {
        viewModelScope.launch(dispatcher) {
            dataSource.getFavorites().collectLatest { results ->
                favorites.postValue(results)
            }
        }
    }

    fun getBankDetails(ifscCode: String) {
        isLoading.value = true
        viewModelScope.launch(dispatcher) {
            val banks = dataSource.getBankDetails(ifscCode)
            bankDetails.postValue(banks)
            isLoading.postValue(false)
        }
    }

    fun onSearchQueryChanged(query: String) {
        viewModelScope.launch(dispatcher) {
            val banksResult = dataSource.onSearchQueryChanged(query)
            searchedBanks.postValue(banksResult)
        }
    }

    fun setFavorite(favoriteBank: FavoriteBank) {
        viewModelScope.launch(dispatcher) {
            dataSource.setFavoriteBank(favoriteBank)
        }
    }

    fun deleteFavorite(ifsc: String) {
        viewModelScope.launch(dispatcher) {
            dataSource.deleteFavoriteBank(ifsc)
        }
    }

    fun getIfscCode(position: Int): String? {
        return favorites.value?.get(position)?.ifsc
    }

    fun isValidPosition(position: Int): Boolean {
        return favorites.value?.let {
            position >= 0 && position < it.size
        } ?: false
    }
}

