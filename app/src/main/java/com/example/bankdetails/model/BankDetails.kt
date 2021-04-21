package com.example.bankdetails.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class BankDetails(
    @SerializedName("IFSC")
    val ifsc: String,
    @SerializedName("BANK")
    val bank: String,
    @SerializedName("BRANCH")
    val branch: String,
    @SerializedName("ADDRESS")
    val address: String,
    @SerializedName("CITY")
    val city: String,
    @SerializedName("CONTACT")
    val contact: String,
    @SerializedName("RTGS")
    val rtgs: Boolean,
    @SerializedName("DISTRICT")
    val district: String,
    @SerializedName("STATE")
    val state: String,
    @SerializedName("UPI")
    val upi: Boolean,
    @SerializedName("CENTER")
    val center: String,
    @SerializedName("MICR")
    val micr: String,
)

@Entity(tableName = "favorite_banks")
data class FavoriteBank(
    @PrimaryKey val IFSC: String,
    val BANK: String
)

