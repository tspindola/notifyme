package com.example.tspindola.notifyme

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.Spannable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.package_item_list.view.*
import android.text.SpannableString
import android.text.style.StyleSpan


class PackageAdapter(val items : MutableList<Package>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.package_item_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nameBold =  "<b>Nome: </b>" + items.get(position).recipient
        val trackingnoBold = "<b>Núm. Rastreio: </b>" + items.get(position).trackingNumber
        val carrierBold = "<b>Remetente: </b>" + items.get(position).sender

        holder.tvNameInfo?.text = Html.fromHtml(nameBold)
        holder.tvTrackingInfo?.text = Html.fromHtml(trackingnoBold)
        holder.tvCarrierInfo?.text = Html.fromHtml(carrierBold)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvNameInfo = view.tv_name_info
    val tvTrackingInfo = view.tv_trackingno_info
    val tvCarrierInfo = view.tv_carrier_info
}