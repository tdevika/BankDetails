package com.example.bankdetails.data.repository

import com.example.bankdetails.data.model.BanksListData
import com.example.bankdetails.ui.home.BanksListAdapter
import javax.inject.Inject

class BanksRepository @Inject constructor() {

    private val banksData = listOf<BanksListData>(
        BanksListData(
            "KARB0000001",
            "Karnataka Bank",
            "RTGS-HO",
            "REGD. & HEAD OFFICE, P.B.NO.599, MAHAVEER CIRCLE, KANKANADY, MANGALORE - 575002",
            "DAKSHINA KANNADA",
            "2228222",
            true,
            "MANGALORE",
            "KARNATAKA"
        ),
        BanksListData(
            "KARB0000002",
            "Karnataka Bank",
            "RTGS-HO",
            "REGD. & HEAD OFFICE, P.B.NO.599, MAHAVEER CIRCLE, KANKANADY, MANGALORE - 575002",
            "DAKSHINA KANNADA",
            "2228222",
            true,
            "MANGALORE",
            "KARNATAKA"
        )
    )

    fun getBanksList(): List<BanksListData> {
        return banksData;
    }
}