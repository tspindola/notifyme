package com.example.tspindola.notifyme

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class PackageInFragment : Fragment(), View.OnClickListener {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val v:View = inflater.inflate(R.layout.fragment_package_in, container, false)
            val buttonAdd = v.findViewById<Button>(R.id.button_new_package_next)

            buttonAdd?.setOnClickListener(this)

            return v
        }

    companion object {
        fun newInstance(): PackageInFragment = PackageInFragment()
    }

    override fun onClick(v:View?){
        when(v?.id){
            R.id.button_new_package_next -> addNewPackage()
        }
    }

    fun addNewPackage(){
        
    }
}