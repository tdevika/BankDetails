package com.example.bankdetails.data.source

import com.example.bankdetails.model.BankDetails
import com.example.bankdetails.model.FavoriteBank
import com.example.bankdetails.utils.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDataSource(
    var favBanks: MutableList<FavoriteBank> = mutableListOf(),
    var bankDetails: MutableList<BankDetails> = mutableListOf(),
    var ifscList: MutableList<String> = mutableListOf()
) : DataSource {

    override suspend fun getFavorites(): Flow<List<FavoriteBank>> {
        return flow { emit(favBanks) }
    }

    override suspend fun getBankDetails(ifscCode: String): BankDetails {
        return bankDetails.first { it.ifsc == ifscCode }
    }

    override suspend fun deleteFavoriteBank(ifscCode: String) {
        favBanks.removeIf { it.ifsc == ifscCode }
    }

    override suspend fun setFavoriteBank(favoriteBank: FavoriteBank) {
        favBanks.add(favoriteBank)
    }

    override suspend fun onSearchQueryChanged(query: String): List<String> {
        return ifscList.filter { it.contains(query, true) }
    }
}

