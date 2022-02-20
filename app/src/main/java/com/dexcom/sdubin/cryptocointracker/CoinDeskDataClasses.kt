package com.dexcom.sdubin.cryptocointracker

import com.google.gson.annotations.SerializedName

data class CoinDeskDataClasses(
    @SerializedName("disclaimer") val myDisclaimer: String,
    @SerializedName("time") val time: TimeData,
    @SerializedName("bpi") val bpi: BitCoinPriceInfo
)

data class TimeData (
    @SerializedName("updated") val updated: String)

data class BitCoinPriceInfo (
    @SerializedName("USD") val usd: USDollarData)

data class USDollarData (
    @SerializedName("rate") val rate: String)