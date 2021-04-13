package com.example.bankdetails.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bank")
data class BankInfo(
    @PrimaryKey val ifsc: String,
    val bank: String,
    val branch: String,
    val address: String,
    val city: String,
    val contact: String,
    val rtgs:Boolean,
    val district:String,
    val state:String
)
