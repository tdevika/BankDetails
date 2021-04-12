package com.example.bankdetails.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banks_list_table")
data class BanksListData(
    @PrimaryKey val IFSC: String,
    val BANK: String,
    val BRANCH: String,
    val ADDRESS: String,
    val CITY: String,
    val CONTACT: String,
    val RTGS:Boolean,
    val DISTRICT:String,
    val STATE:String
)
