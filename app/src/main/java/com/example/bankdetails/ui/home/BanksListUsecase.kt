package com.example.bankdetails.ui.home

import com.example.bankdetails.data.model.BanksListData
import com.example.bankdetails.data.repository.BanksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BanksListUsecase @Inject constructor(val repository: BanksRepository) {
    suspend fun getBankListData() :List<BanksListData> =
        withContext(Dispatchers.IO) {
            repository.getBanksList()
    }
}