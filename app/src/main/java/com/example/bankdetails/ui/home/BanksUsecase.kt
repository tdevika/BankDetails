package com.example.bankdetails.ui.home

import com.example.bankdetails.data.model.BankInfo
import com.example.bankdetails.data.repository.BanksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BanksUsecase @Inject constructor(val repository: BanksRepository) {
    suspend fun getBankListData() :List<BankInfo> =
        withContext(Dispatchers.IO) {
            repository.getBanks()
    }
}