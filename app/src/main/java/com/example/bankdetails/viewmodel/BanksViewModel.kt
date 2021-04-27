package com.example.bankdetails.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.bankdetails.model.BankDetails
import com.example.bankdetails.model.FavoriteBank
import com.example.bankdetails.utils.DataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.kodein.di.DIAware
import org.kodein.di.android.x.di


class BanksViewModel(
    private val dataSource: DataSource,
    application: Application,
    private val dispatcher: CoroutineDispatcher
) :AndroidViewModel(application) {


    val bankDetails = MutableLiveData<BankDetails>()
    val searchedBanks = MutableLiveData<List<String>>()
    private val _favorites = MutableLiveData<List<FavoriteBank>>()
    val favorites: LiveData<List<FavoriteBank>> = _favorites
    val isLoading = MutableLiveData<Boolean>()

    init {
        getFavoriteBanks()
    }

    fun getFavoriteBanks() {
        viewModelScope.launch(dispatcher) {
            dataSource.getFavorites().collectLatest { results ->
                _favorites.postValue(results)
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
        return _favorites.value?.get(position)?.ifsc
    }

    fun isValidPosition(position: Int): Boolean {
        return _favorites.value?.let {
            position >= 0 && position < it.size
        } ?: false
    }
}

