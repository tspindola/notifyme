package com.example.tspindola.notifyme

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.onurkaganaldemir.ktoastlib.KToast
import kotlinx.android.synthetic.main.fragment_package_out.*

class PackageOutFragment : Fragment(),View.OnClickListener {
    private val SELECTED_LIST: String = "SELECTED_LIST"
    private val packages: MutableList<Package> = mutableListOf()
    private lateinit var dbReference: DatabaseReference
    private val selectedList: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val v:View = inflater.inflate(R.layout.fragment_package_out, container, false)

        val fab_next = v.findViewById<FloatingActionButton>(R.id.fab_next)
        fab_next.setOnClickListener(this)

        dbReference = FirebaseDatabase.getInstance().reference
        getPackagesList()

        return v
    }

    override fun onClick(v:View?){
        when(v?.id){
            R.id.fab_next ->{
                if(selectedList.size == 0) {
                    showFailToast("Selecione pelo menos um pacote!")
                }
                else {
                    val intent = Intent(context, CheckoutActivity::class.java)
                    val packageList:ArrayList<Package> = ArrayList()
                    populatePackageList(packageList)
                    intent.putParcelableArrayListExtra(SELECTED_LIST,packageList)
                    startActivity(intent)
                }
            }
        }
    }

    fun populatePackageList(packageList:ArrayList<Package>){
        for(p in packages){
            if(selectedList.contains(p.uuid))
                packageList.add(p)
        }
    }

    fun manageSelectedList(uuid:String){
        if(selectedList.contains(uuid))
            selectedList.remove(uuid)
        else
            selectedList.add(uuid)
    }

    fun onPackageClick(position:Int){
        manageSelectedList(packages[position].uuid)
    }

    fun populateTheRecyclerView(){
        rv_package_list.layoutManager = LinearLayoutManager(this.context)
        rv_package_list.adapter = PackageAdapter(packages,this.context!!,{a:Int -> onPackageClick(a)})
    }

    fun getPackagesList(){
        val packageListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                packages.clear()
                dataSnapshot.children.mapNotNullTo(packages) { it.getValue<Package>(Package::class.java) }
                populateTheRecyclerView()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                showFailToast("Falha ao obter os dados!")
            }
        }
        dbReference.child("Package").addValueEventListener(packageListener)
    }

    fun showFailToast(message:String){
        KToast.customColorToast(this.requireActivity(), message,
                Gravity.CENTER, KToast.LENGTH_LONG, R.color.red, R.drawable.ic_x);
    }

    fun showSucessToast(message:String){
        KToast.customColorToast(this.requireActivity(), message, Gravity.CENTER, KToast.LENGTH_LONG,
                R.color.green, R.drawable.ic_check);
    }

    companion object {
        fun newInstance(): PackageOutFragment = PackageOutFragment()
    }


}