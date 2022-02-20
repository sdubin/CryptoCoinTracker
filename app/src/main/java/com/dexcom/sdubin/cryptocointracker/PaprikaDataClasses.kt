package com.dexcom.sdubin.cryptocointracker

import com.google.gson.annotations.SerializedName

data class PaprikaDataClasses (

    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("rank") val rank: String,
    @SerializedName("is_new") val isNew: Boolean,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("type") val type: String)