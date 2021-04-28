package com.example.bankdetails.utils

import com.example.bankdetails.database.BanksDao
import com.example.bankdetails.model.BankDetails
import com.example.bankdetails.model.FavoriteBank
import com.example.bankdetails.network.BanksApiService
import kotlinx.coroutines.flow.*

class BankDataSource(private val banksDao: BanksDao, private val apiService: BanksApiService) :
    DataSource {

    override suspend fun getFavorites(): Flow<List<FavoriteBank>> {
        return banksDao.getFavoriteBank()
    }

    override suspend fun getBankDetails(ifscCode: String): BankDetails {
        val isFavourite = banksDao.isBankAddedToFav(ifscCode)
        return apiService.getBankDetails(ifscCode).apply {
            isFav = isFavourite
        }
    }

    override suspend fun deleteFavoriteBank(ifscCode: String) {
        banksDao.deleteFavoriteBank(ifscCode)
    }

    override suspend fun setFavoriteBank(favoriteBank: FavoriteBank) {
        banksDao.setFavoriteBank(favoriteBank)
    }

    override suspend fun onSearchQueryChanged(query: String): List<String> {
        return DataSource.IFSC_DATA.filter {
            it.contains(query, true)
        }
    }
}