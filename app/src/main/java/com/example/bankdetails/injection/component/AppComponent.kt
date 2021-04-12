package com.example.bankdetails.injection.component

import android.content.Context
import com.example.bankdetails.injection.module.DatabaseModule
import com.example.bankdetails.injection.module.ViewModelModule
import com.example.bankdetails.ui.home.BanksListFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DatabaseModule::class,ViewModelModule::class])
interface AppComponent {
    fun inject(banksListFragment: BanksListFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}