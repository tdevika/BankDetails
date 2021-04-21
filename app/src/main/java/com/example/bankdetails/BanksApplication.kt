package com.example.bankdetails

import android.app.Application
import androidx.room.Room
import com.example.bankdetails.database.BanksDao
import com.example.bankdetails.database.BanksDatabase
import com.example.bankdetails.network.BanksApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BanksApplication : Application(), DIAware {

    override val di by DI.lazy {

        bind() from singleton {String()}

        bind<BanksDatabase>("database") with singleton {
            Room.databaseBuilder(
                applicationContext,
                BanksDatabase::class.java,
                "banks_database"
            ).build()
        }

        bind<BanksDao>("databaseDao") with singleton {
            instance<BanksDatabase>("database").banksDao()
        }

        bind<Interceptor>("loadingInterceptor") with singleton {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            interceptor
        }


        bind<OkHttpClient>("httpClient") with singleton {
            val client = OkHttpClient
                .Builder()
                .addInterceptor(instance<Interceptor>("loadingInterceptor"))
            client.build()
        }

        bind<Retrofit>("retrofit") with singleton {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("https://ifsc.razorpay.com/")
                .client(instance("httpClient"))
                .build()
        }

        bind<BanksApiService>("apiService") with singleton {
            instance<Retrofit>("retrofit").create(BanksApiService::class.java)
        }

    }
}
