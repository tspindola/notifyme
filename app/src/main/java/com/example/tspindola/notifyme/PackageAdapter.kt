package com.example.tspindola.notifyme

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.package_item_list.view.*

class PackageAdapter(val items : MutableList<Package>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.package_item_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNameInfo?.text = items.get(position).recipient
        holder.tvTrackingInfo?.text = items.get(position).trackingNumber
        holder.tvCarrierInfo?.text = items.get(position).sender
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvNameInfo = view.tv_name_info
    val tvTrackingInfo = view.tv_trackingno_info
    val tvCarrierInfo = view.tv_carrier_info
}