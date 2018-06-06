package com.example.tspindola.notifyme

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PackageOutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_package_out, container, false)

    companion object {
        fun newInstance(): PackageOutFragment = PackageOutFragment()
    }
}