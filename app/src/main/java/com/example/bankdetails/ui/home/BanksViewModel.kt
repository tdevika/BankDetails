package com.example.bankdetails.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankdetails.data.model.BankInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class BanksViewModel @Inject constructor(private val banksUsecase: BanksUsecase) :
    ViewModel() {

    private var banksList = MutableLiveData<List<BankInfo>>()
    val _banksList: LiveData<List<BankInfo>> = banksList

    init {
        getBanksListData()
    }

    private fun getBanksListData() {

        viewModelScope.launch {
            banksList.value=banksUsecase.getBankListData()
        }
    }

}