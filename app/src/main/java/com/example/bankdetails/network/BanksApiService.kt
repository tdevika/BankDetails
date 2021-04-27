package com.example.bankdetails.network

import com.example.bankdetails.model.BankDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface BanksApiService {

    @GET("{url}")
    suspend fun getBankDetails(@Path("url") url:String) :BankDetails
}