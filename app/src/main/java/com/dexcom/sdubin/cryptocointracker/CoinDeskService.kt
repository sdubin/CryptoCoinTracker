package com.dexcom.sdubin.cryptocointracker

import retrofit2.Call
import retrofit2.http.GET

public interface CoinDeskService {

    @GET("currentprice.json")
    fun getCurrentPrice(): Call<CoinDeskDataClasses>

}