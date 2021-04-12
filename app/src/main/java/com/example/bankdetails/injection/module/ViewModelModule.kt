package com.example.bankdetails.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankdetails.injection.scope.ViewModelScope
import com.example.bankdetails.ui.details.BankDetailsViewModel
import com.example.bankdetails.ui.home.BanksViewModel
import com.example.bankdetails.utils.BanksViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun provideViewModelFactory(banksViewModelFactory: BanksViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelScope(BanksViewModel::class)
    abstract fun bindBanksListViewModel(banksViewModel: BanksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(BankDetailsViewModel::class)
    abstract fun bindBankDetailsViewModel(bankDetailsViewModel: BankDetailsViewModel): ViewModel
}