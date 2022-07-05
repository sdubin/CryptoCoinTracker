package com.dexcom.sdubin.cryptocointracker.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dexcom.sdubin.cryptocointracker.CoinDeskDataClasses
import com.dexcom.sdubin.cryptocointracker.CoinDeskService
import com.dexcom.sdubin.cryptocointracker.R
import com.dexcom.sdubin.cryptocointracker.TimeData
import com.dexcom.sdubin.cryptocointracker.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal
import java.math.BigDecimal.ROUND_UP
import java.math.MathContext
import java.time.LocalDate
import kotlin.math.roundToInt


private const val TAG = "HomeFragment"

private const val BASE_URL_COINDESK = "https://api.coindesk.com/v1/bpi/"

class HomeFragment : Fragment() {

//    lateinit var tvBitcoinPrice: TextView
//    lateinit var tvDate: TextView

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

//        val root = inflater.inflate(R.layout.fragment_home, null) as ViewGroup
        val tvDate = root.findViewById<View>(R.id.tvDate) as TextView
        val tvBitcoinPrice = root.findViewById<View>(R.id.tvBitcoinPrice) as TextView
        tvBitcoinPrice.text = "$$$"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_COINDESK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val coinDeskService =
            retrofit.create(CoinDeskService::class.java)

        //CoinDeskDataClasses
        val result = coinDeskService.getCurrentPrice().enqueue(object :
            Callback<CoinDeskDataClasses> {
            override fun onResponse(call: Call<CoinDeskDataClasses>, response: Response<CoinDeskDataClasses>) {
                Log.d(TAG, "onResponse $response")

                val body = response.body()
                if(body == null) {
                    // body data is not properly parsed, log warning & return
                    Log.w(TAG, "Error parsing JSON data from Coindesk")
                    return
                }

//                                var timeInfo: TimeData = body.time.updated

                var timeUTC = body.time.updated
                tvDate.text = timeUTC
//                val timeLocal = LocalDate.of()
//                var temp2 = String.format("%.2f", temp)

                var bitcoinPrice = body.bpi.usd.rate
                val dollars = bitcoinPrice.split(".")[0]
                val cents = bitcoinPrice.split(".")[1].toFloat().div(100).roundToInt().toString()
                tvBitcoinPrice.text = "$$dollars.$cents"

            }

            override fun onFailure(call: Call<CoinDeskDataClasses>, t: Throwable) {
                Log.d(TAG, "onFailure $t")
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}