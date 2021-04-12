package com.example.bankdetails.data.repository

import com.example.bankdetails.data.model.BankInfo
import javax.inject.Inject

class BanksRepository @Inject constructor() {

    private val banksData = hashMapOf<String,BankInfo>().apply {
        put("KARB0000001", BankInfo(
            "KARB0000001",
            "Karnataka Bank",
            "RTGS-HO",
            "REGD. & HEAD OFFICE, P.B.NO.599, MAHAVEER CIRCLE, KANKANADY, MANGALORE - 575002",
            "DAKSHINA KANNADA",
            "2228222",
            true,
            "MANGALORE",
            "KARNATAKA")
        )
        put("ABHY0065306", BankInfo(
            "ABHY0065306",
            "Abhyudaya Co-op Bank Ltd",
            "Mangalore",
            "Laxmidas Complex, Maidan Rd, Opp. Bus Stand, Mangalore, 575 001",
            "Mangalore",
            "2228222",
            true,
            "Dakshina Kannada",
            "Karnataka")
        )
        put("ANDB0001082", BankInfo(
            "ANDB0001082",
            "Andhra Bank",
            "Air Bypass Road",
            "Air Bypassw Road, Tirupathi - 517 502",
            "Tirupati",
            "001082",
            true,
            "Chittor",
            "Andhra Pradesh")
        )
        put("BDBL0001498", BankInfo(
            "BDBL0001498",
            "Bandhan Bank Limited",
            "East Patel Nagar",
            "Plot No.30, Building-28, Ground Floor, P.o.east Patel Nagar, New Delhi, Pin -110008",
            "New Delhi",
            "001498",
            true,
            "Central Delhi",
            "Delhi")
        )
        put("BKID0008587", BankInfo(
            "BKID0008587",
            "Bank Of India",
            "Kadavanthara",
            "K P Vallon Road Kadavanthara Junction Kochi 682020",
            "Ernakulam",
            "008587",
            true,
            "Kochi",
            "Kerala")
        )
        put("CNRB0006067", BankInfo(
            "CNRB0006067",
            "Canara Bank",
            "Ajitwal",
            "Near Mukat Sheedgunj Gurudwara Railway Station Road Ajitwal Punjab 142053",
            "Moga",
            "006067",
            true,
            "Ajitwal",
            "Punjab")
        )
        put("CIUB0000444", BankInfo(
            "CIUB0000444",
            "City Union Bank Ltd",
            "Bhilwara",
            "Shop No 3-7, Srinath Tower, Gangapur Choraha, Pur Road, Bhilwara-311001",
            "Bhilwara",
            "000222",
            true,
            "Bhilwara",
            "Rajasthan")
        )
        put("NOSC0000MUM", BankInfo(
            "NOSC0000MUM",
            "The Bank Of Nova Scotia",
            "Nariman Point",
            "B Wing Mittal Tower, Gr Floor, Nariman Point, Mumbai - 21",
            "Mumbai",
            "000MUM",
            true,
            "Greater Bombay",
            "Maharashtra")
        )
        put("ANDB0001082", BankInfo(
            "ANDB0001082",
            "Andhra Bank",
            "Air Bypass Road",
            "Air Bypassw Road, Tirupathi - 517 502",
            "Tirupati",
            "001082",
            true,
            "Chittor",
            "Andhra Pradesh")
        )
        put("SYNB0008612", BankInfo(
            "SYNB0008612",
            "Syndicate Bank",
            "Agra Cantonment",
            "Imperial Cinema Compound, Sadar Bazar, Cantonment, Agra, Agra, Uttar Pradesh, 282 001",
            "Agra",
            "008612",
            true,
            "Agra",
            "Uttar Pradesh")
        )
    }

    fun getBanks(): List<BankInfo> {
        return banksData.values.toList()
    }

    fun getBankDetails(ifscCode:String): BankInfo? {
            return banksData[ifscCode]
    }

}