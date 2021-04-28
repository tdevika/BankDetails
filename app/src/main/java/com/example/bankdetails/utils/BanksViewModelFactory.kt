package com.example.bankdetails.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankdetails.viewmodel.BanksViewModel
import kotlinx.coroutines.CoroutineDispatcher

class BanksViewModelFactory(
    private val application: Application,
    private val bankDataSource: BankDataSource,
    private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        BanksViewModel(bankDataSource, application,dispatcher) as T
}