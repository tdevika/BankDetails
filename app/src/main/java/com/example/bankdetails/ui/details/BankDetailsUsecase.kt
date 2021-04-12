package com.example.bankdetails.ui.details

import com.example.bankdetails.data.model.BankInfo
import com.example.bankdetails.data.repository.BanksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BankDetailsUsecase @Inject constructor(
    private val banksRepository: BanksRepository
) {
    suspend fun getBankDetails(ifscCode: String): BankInfo? =
        withContext(Dispatchers.IO) {
            banksRepository.getBankDetails(ifscCode)
        }
}