package com.example.bankdetails

import android.app.Application
import com.example.bankdetails.injection.component.DaggerAppComponent

class BanksApplication : Application() {
    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}