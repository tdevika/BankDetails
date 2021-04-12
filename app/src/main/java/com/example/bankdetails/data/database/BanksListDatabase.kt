package com.example.bankdetails.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bankdetails.data.model.BanksListData
import java.security.AccessControlContext

@Database(entities = [BanksListData::class],version = 1,exportSchema = false )
abstract class BanksListDatabase :RoomDatabase(){
abstract fun banksListDao():BanksListDao

companion object{
    @Volatile
    var INSTANCE :BanksListDatabase?= null

    fun getInstance(context: Context):BanksListDatabase?{
        if(INSTANCE==null) {
            synchronized(this) {
                    Room.databaseBuilder(context, BanksListDatabase::class.java, "banks_database")
                        .fallbackToDestructiveMigration().build()
            }
        }
       return INSTANCE
    }
}
}