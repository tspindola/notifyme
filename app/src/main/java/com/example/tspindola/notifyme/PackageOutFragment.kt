package com.example.tspindola.notifyme

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.onurkaganaldemir.ktoastlib.KToast
import kotlinx.android.synthetic.main.fragment_package_out.*

class PackageOutFragment : Fragment() {
    private val packages: MutableList<Package> = mutableListOf()
    private lateinit var dbReference: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val v:View? = inflater.inflate(R.layout.fragment_package_out, container, false)

        dbReference = FirebaseDatabase.getInstance().reference
        getPackagesList()

        return v
    }

    fun test(){
        rv_package_list.layoutManager = LinearLayoutManager(this.context)
        rv_package_list.adapter = PackageAdapter(packages,this.context!!)
    }

    fun getPackagesList(){
        val packageListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                packages.clear()
                dataSnapshot.children.mapNotNullTo(packages) { it.getValue<Package>(Package::class.java) }
                test()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                showFailToast()
            }
        }
        dbReference.child("Package").addValueEventListener(packageListener)
    }

    fun showFailToast(){
        KToast.customColorToast(this.requireActivity(), "Falha ao obter os dados!",
                Gravity.CENTER, KToast.LENGTH_LONG, R.color.red, R.drawable.ic_x);
    }

    companion object {
        fun newInstance(): PackageOutFragment = PackageOutFragment()
    }


}