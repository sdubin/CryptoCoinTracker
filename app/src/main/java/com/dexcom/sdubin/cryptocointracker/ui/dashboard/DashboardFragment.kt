package com.dexcom.sdubin.cryptocointracker.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexcom.sdubin.cryptocointracker.PaprikaDataClasses
import com.dexcom.sdubin.cryptocointracker.PaprikaService
import com.dexcom.sdubin.cryptocointracker.R
import com.dexcom.sdubin.cryptocointracker.databinding.FragmentDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "DashboardFragment"

private const val BASE_URL_PAPRIKA = "https://api.coinpaprika.com/v1/"

class DashboardFragment : Fragment() {

    lateinit var rvAltCoin: RecyclerView
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        rvAltCoin = root.findViewById<View>(R.id.rvAltCoin) as RecyclerView
        rvAltCoin.layoutManager = LinearLayoutManager(root.context)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //         requireActivity().applicationContext
        val altCoins = mutableListOf<PaprikaDataClasses>()
        val adapter = AltCoinAdapter(requireContext(), altCoins)

        rvAltCoin.adapter = adapter

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_PAPRIKA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val paprikaService =
            retrofit.create(PaprikaService::class.java)

        val result = paprikaService.getAltCurrentPrice().enqueue(object : Callback<List<PaprikaDataClasses>> {
            override fun onResponse(
                call: Call<List<PaprikaDataClasses>>,
                response: Response<List<PaprikaDataClasses>>
            ) {

                Log.d(TAG, "onResponse $response")
                val body = response.body()
                if(body == null) {
                    // body data is not properly parsed, log warning & return
                    Log.w(TAG, "Error parsing JSON data from Paprika")
                    return
                }

                var maxLoop = 0;
                for (coin in body) {

                    Log.w(TAG, "$coin")
                    maxLoop++

                    if(coin.name != "Bitcoin") {
                        altCoins.add(coin)
                        adapter.notifyDataSetChanged()
                    }

//                    coin.id
//                    coin.name
//                    coin.rank
//                    coin.symbol
//                    coin.type
//                    coin.isNew
//                    coin.isActive

                    //Keep list of items small
                    if(maxLoop > 20) {break}

                }
            }

            override fun onFailure(call: Call<List<PaprikaDataClasses>>, t: Throwable) {
                Log.d(TAG, "onFailure $t")
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}