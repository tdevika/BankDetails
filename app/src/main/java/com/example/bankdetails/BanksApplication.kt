package com.example.bankdetails

import android.app.Application
import androidx.room.Room
import com.example.bankdetails.database.BanksDao
import com.example.bankdetails.database.BanksDatabase
import org.kodein.di.*

class BanksApplication : Application(), DIAware {

    override val di by DI.lazy {

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

    }
}
