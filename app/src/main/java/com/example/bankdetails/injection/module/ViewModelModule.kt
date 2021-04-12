package com.example.bankdetails.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankdetails.injection.scope.ViewModelScope
import com.example.bankdetails.ui.home.BanksListViewModel
import com.example.bankdetails.utils.BanksListViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun provideViewModelFactory(banksListViewModelFactory: BanksListViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelScope(BanksListViewModel::class)
    abstract fun bindBanksListViewModel(banksListViewModel: BanksListViewModel): ViewModel
}