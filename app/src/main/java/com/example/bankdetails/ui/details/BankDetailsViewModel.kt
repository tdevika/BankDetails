package com.example.bankdetails.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankdetails.data.model.BankInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class BankDetailsViewModel @Inject constructor(
    private val bankDetailsUsecase: BankDetailsUsecase
) : ViewModel() {
    val bankDetails = MutableLiveData<BankInfo>()

    fun getBankDetails(ifscCode: String) {
        viewModelScope.launch {
            bankDetails.value = bankDetailsUsecase.getBankDetails(ifscCode)
        }
    }
}