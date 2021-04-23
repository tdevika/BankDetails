package com.example.bankdetails.api

import com.example.bankdetails.network.BanksApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class BanksApiServiceTest {
    private lateinit var service: BanksApiService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(BanksApiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getBankDetails() {
        enqueueResponse("bank-details.json")
        val bankDetails = runBlocking { service.getBanks("KARB0000001") }
        Assert.assertEquals(bankDetails.state,"KARNATAKA")
        Assert.assertEquals(bankDetails.rtgs,false)
        Assert.assertEquals(bankDetails.city,"DAKSHINA KANNADA")
        Assert.assertEquals(bankDetails.district,"MANGALORE")
        Assert.assertEquals(bankDetails.branch,"Karnataka Bank IMPS")
        Assert.assertEquals(bankDetails.contact,"2228222")
        Assert.assertEquals(bankDetails.upi,true)
        Assert.assertEquals(bankDetails.address,"REGD. & HEAD OFFICE, P.B.NO.599, MAHAVEER CIRCLE, KANKANADY, MANGALORE - 575002")
    }

    private fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}