package com.example.tspindola.notifyme

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.onurkaganaldemir.ktoastlib.KToast
import kotlinx.android.synthetic.main.fragment_package_in.*

class PackageInFragment : Fragment(), View.OnClickListener {
    private lateinit var dbReference: DatabaseReference
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val v:View = inflater.inflate(R.layout.fragment_package_in, container, false)

            dbReference = FirebaseDatabase.getInstance().reference
            val buttonAdd = v.findViewById<Button>(R.id.button_new_package_next)


            buttonAdd?.setOnClickListener(this)

            return v
        }

    companion object {
        fun newInstance(): PackageInFragment = PackageInFragment()
    }

    override fun onClick(v:View?){
        when(v?.id){
            R.id.button_new_package_next -> {
                addNewPackage()
                clearFields()
            }
            else -> {

            }
        }
    }

    fun validateFields():Boolean{
        var isValid:Boolean = true
        if(input_name.text.toString().length == 0 ||
           input_trackingno.text.toString().length == 0 ||
           input_shelf.text.toString().length == 0 ||
           input_carrier.text.toString().length == 0)
            isValid = false
        return isValid;
    }

    fun showSucessToast(){
        KToast.customColorToast(this.requireActivity(), "Pacote Adicionado com sucesso!",
                Gravity.CENTER, KToast.LENGTH_LONG, R.color.green, R.drawable.ic_check);
    }

    fun showFailToast(){
        KToast.customColorToast(this.requireActivity(), "Falha! Confira os dados e tente novamente.",
                Gravity.CENTER, KToast.LENGTH_LONG, R.color.red, R.drawable.ic_x);
    }

    fun clearFields(){
        input_name.setText("")
        input_carrier.setText("")
        input_shelf.setText("")
        input_trackingno.setText("")
        input_observations.setText("")
        checkbox_fragile.isChecked = false
        checkbox_heavy.isChecked = false
        checkbox_urgent.isChecked = false
    }

    fun addNewPackage(){
        if(validateFields())
        {
            val key = dbReference.child("Package").push().key
            val newPackage = Package(key!!,input_trackingno.text.toString(),
                    input_name.text.toString(),input_shelf.text.toString(),
                    input_carrier.text.toString(),checkbox_urgent.isChecked,
                    checkbox_fragile.isChecked,checkbox_heavy.isChecked,false,
                    input_observations.text.toString())
            dbReference.child("Package").child(key).setValue(newPackage)
            showSucessToast()
        }
        else showFailToast()
    }
}