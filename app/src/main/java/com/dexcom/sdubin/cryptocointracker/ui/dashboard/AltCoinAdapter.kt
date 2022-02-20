package com.dexcom.sdubin.cryptocointracker.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dexcom.sdubin.cryptocointracker.PaprikaDataClasses
import com.dexcom.sdubin.cryptocointracker.R

class AltCoinAdapter(val context: Context, val altCoins: List<PaprikaDataClasses>) :
    RecyclerView.Adapter<AltCoinAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(altCoinName: PaprikaDataClasses) {
            itemView.findViewById<TextView>(R.id.tvAltCoinName).text = altCoinName.name
            itemView.findViewById<TextView>(R.id.tvSymbol).text = altCoinName.symbol
            itemView.findViewById<TextView>(R.id.tvId).text = altCoinName.id
            itemView.findViewById<TextView>(R.id.tvRank).text = altCoinName.rank
//            itemView.tvAltCoinName.text = altCoinName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_alt_coin, parent, false))
    }

    override fun getItemCount() = altCoins.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val altCoinName = altCoins[position]
        holder.bind(altCoinName)
    }

}
