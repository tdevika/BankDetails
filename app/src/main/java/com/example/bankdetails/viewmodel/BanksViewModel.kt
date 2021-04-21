package com.example.bankdetails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bankdetails.database.BanksDao
import com.example.bankdetails.model.BankDetails
import com.example.bankdetails.model.FavoriteBank
import com.example.bankdetails.network.BanksApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance


class BanksViewModel(application: Application) : AndroidViewModel(application), DIAware {

    override val di by di()

    private val apiService: BanksApiService by instance("apiService")
    private val banksDao: BanksDao by instance("databaseDao")

    val bankDetails = MutableLiveData<BankDetails>()
    val banks = MutableLiveData<List<String>>()
    val favorites = MutableLiveData<List<FavoriteBank>>()
    val isLoading = MutableLiveData<Boolean>()



    private val banksData = listOf(
        "KARB0000001",
        "KARB0000002",
        "KARB0000003",
        "KARB0000004",
        "KARB0000005",
        "ABHY0065306",
        "ANDB0001082",
        "BDBL0001498",
        "BKID0008587",
        "CNRB0006067",
        "CIUB0000444",
        "NOSC0000MUM",
        "ANDB0001082",
        "SYNB0008612",
    )


    init {
        getFavoriteBanks()
    }

    fun getFavoriteBanks() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                banksDao.getFavoriteBank().collectLatest {
                    favorites.postValue(it)
                }
            }
        }
    }

    fun getBankDetails(ifscCode: String) {
        isLoading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val banks = apiService.getBanks(ifscCode).apply {
                    favorites.value?.let { it ->
                        it.forEach {
                            if (this.ifsc == it.ifsc) {
                                this.isFav = true
                            }
                        }
                    }
                }
                bankDetails.postValue(banks)
                isLoading.postValue(false)
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        viewModelScope.launch {
            val banksResult = banksData.filter { it.contains(query, true) }
            banks.postValue(banksResult)
        }
    }

    fun setFavorite() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                bankDetails.value?.let {
                    banksDao.setFavoriteBank(FavoriteBank(it.ifsc, it.bank))
                }
            }
        }
    }

    fun getIfscCode(position: Int): String? {
        return favorites.value?.get(position)?.ifsc
    }

    fun deleteFavorite(ifsc: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                banksDao.deleteFavoriteBank(ifsc)
            }
        }
    }

    fun isValidPosition(position: Int): Boolean {
        return favorites.value?.let {
            position >= 0 && position < it.size
        } ?: false
    }
}