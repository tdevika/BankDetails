package com.example.bankdetails.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bankdetails.model.FavoriteBank

@Database(entities = [FavoriteBank::class], version = 1, exportSchema = false)
abstract class BanksDatabase : RoomDatabase() {
    abstract fun banksDao(): BanksDao
}