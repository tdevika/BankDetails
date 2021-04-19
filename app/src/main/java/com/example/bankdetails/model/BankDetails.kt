package com.example.bankdetails.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class BankDetails(
    val IFSC: String,
    val BANK: String,
    val BRANCH: String,
    val ADDRESS: String,
    val CITY: String,
    val CONTACT: String,
    val RTGS: Boolean,
    val DISTRICT: String,
    val STATE: String,
    val UPI: Boolean,
    val BANKCODE: String,
    val CENTRE: String,
    val MICR: String,
    val NEFT: Boolean
)

@Entity(tableName = "favorite_banks")
data class FavoriteBank(
    @PrimaryKey val IFSC: String,
    val BANK: String
)

