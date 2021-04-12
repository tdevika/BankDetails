package com.example.bankdetails.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankdetails.data.model.BanksListData
import kotlinx.coroutines.launch
import javax.inject.Inject

class BanksListViewModel @Inject constructor(private val banksListUsecase: BanksListUsecase) :
    ViewModel() {

    private var banksList = MutableLiveData<List<BanksListData>>()
    val _banksList: LiveData<List<BanksListData>> = banksList

    init {
        getBanksListData()
    }

    private fun getBanksListData() {

        viewModelScope.launch {
            banksList.value=banksListUsecase.getBankListData()
        }
    }

}