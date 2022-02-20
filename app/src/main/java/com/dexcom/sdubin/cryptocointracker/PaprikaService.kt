package com.dexcom.sdubin.cryptocointracker

import retrofit2.Call
import retrofit2.http.GET

interface PaprikaService {
    @GET("coins")
    fun getAltCurrentPrice(): Call<List<PaprikaDataClasses>>

}