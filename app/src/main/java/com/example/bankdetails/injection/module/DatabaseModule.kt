package com.example.bankdetails.injection.module

import android.content.Context
import com.example.bankdetails.data.database.BanksListDao
import com.example.bankdetails.data.database.BanksListDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): BanksListDatabase? =
        BanksListDatabase.getInstance(context)

    @Provides
    fun provideDao(banksListDatabase: BanksListDatabase): BanksListDao =
        banksListDatabase.banksListDao()
}