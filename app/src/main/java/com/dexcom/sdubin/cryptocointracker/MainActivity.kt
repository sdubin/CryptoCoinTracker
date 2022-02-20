package com.dexcom.sdubin.cryptocointracker

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dexcom.sdubin.cryptocointracker.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"

private const val BASE_URL_COINDESK = "https://api.coindesk.com/v1/bpi/"
private const val BASE_URL_PAPRIKA = "https://api.coinpaprika.com/v1/coins"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {  }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL_COINDESK)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val coinDeskService =
//            retrofit.create(CoinDeskService::class.java)
//
//        val result = coinDeskService.getCurrentPrice().enqueue(object :
//            Callback<CoinDeskDataClasses> {
//            override fun onResponse(
//                call: Call<CoinDeskDataClasses>,
//                response: Response<CoinDeskDataClasses>
//            ) {
//
//            }
//
//            override fun onFailure(call: Call<CoinDeskDataClasses>, t: Throwable) {
//
//            }
//
//        })
    }
}