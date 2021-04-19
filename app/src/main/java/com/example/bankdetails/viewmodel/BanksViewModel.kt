package com.example.bankdetails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bankdetails.database.BanksDao
import com.example.bankdetails.model.BankDetails
import com.example.bankdetails.network.BanksApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance


class BanksViewModel(application: Application) : AndroidViewModel(application), DIAware {

    override val di by di()

   private val apiService: BanksApiService by instance("apiService")

    val bankDetails = MutableLiveData<BankDetails>()
    val banks = MutableLiveData<List<String>>()


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

    fun getBankDetails(ifscCode: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                bankDetails.postValue(apiService.getBanks(ifscCode))
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        viewModelScope.launch {
            val banksResult = banksData.filter { it.contains(query, true) }
            banks.postValue(banksResult)
        }
    }

}