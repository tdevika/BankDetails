package com.example.bankdetails.injection.component

import android.content.Context
import com.example.bankdetails.injection.module.DatabaseModule
import com.example.bankdetails.injection.module.ViewModelModule
import com.example.bankdetails.ui.details.BankDetailsFragment
import com.example.bankdetails.ui.home.BanksFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DatabaseModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(banksFragment: BanksFragment)
    fun inject(bankDetailsFragment: BankDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}