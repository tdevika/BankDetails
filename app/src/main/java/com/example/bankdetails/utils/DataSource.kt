package com.example.bankdetails.utils

import com.example.bankdetails.model.BankDetails
import com.example.bankdetails.model.FavoriteBank
import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun getFavorites(): Flow<List<FavoriteBank>>
    suspend fun getBankDetails(ifscCode: String): BankDetails
    suspend fun deleteFavoriteBank(ifscCode: String)
    suspend fun setFavoriteBank(favoriteBank: FavoriteBank)
    suspend fun onSearchQueryChanged(query: String): List<String>

    companion object {
        val FAVORITE_BANK = FavoriteBank("KARB0000001", "Karnataka Bank")
        val IFSC_DATA = listOf(
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

        val FAVORITE_BANKS =
            object : ArrayList<FavoriteBank>() {
                init {
                    add(FAVORITE_BANK)
                    add(FavoriteBank("KARB0000001", "Karnataka Bank"))
                    add(FavoriteBank("BDBL0001498", "BDBL Bank"))
                }
            }

        val BANK_DETAILS = BankDetails(
            "KARB0000001",
            "Karnataka Bank",
            "Karnataka Bank IMPS",
            "REGD. & HEAD OFFICE, P.B.NO.599, MAHAVEER CIRCLE, KANKANADY, MANGALORE - 575002",
            "DAKSHINA KANNADA",
            "2228222",
            true,
            "MANGALORE",
            "KARNATAKA",
            true,
            "",
            "NA"

        )
    }
}