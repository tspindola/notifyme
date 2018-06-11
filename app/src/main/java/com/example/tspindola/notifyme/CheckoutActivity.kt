package com.example.tspindola.notifyme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_checkout.*
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.Manifest
import android.app.Activity
import android.widget.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat
import java.util.*


class CheckoutActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    val SELECTED_LIST: String = "SELECTED_LIST"
    val RC_PERMISSION_READ_EXTERNAL_STORAGE = 1
    val RC_IMAGE_GALLERY = 2

    var statusList = arrayOf("Retirado pelo destinatário",
            "Entregue ao destinatário",
            "Retirado por terceiro",
            "Entrada incorreta",
            "Pacote perdido/extraviado")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val packages: ArrayList<Package>
        packages = intent.getParcelableArrayListExtra<Package>(SELECTED_LIST)

        val spinnerStatus = findViewById<Spinner>(R.id.spinner_checkout_status)
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, statusList)

        spinnerStatus.setOnItemSelectedListener(this)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStatus.setAdapter(aa)

        val image_evidence1 = findViewById<ImageView>(R.id.imageview_evidence1)
        image_evidence1.setOnClickListener(this)

        rv_checkout.layoutManager = LinearLayoutManager(this)
        rv_checkout.adapter = PackageAdapter(packages,this,{a:Int -> onPackageClick(a)})
    }

    fun onPackageClick(position:Int){

    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imageview_evidence1 ->{
                uploadImage(v)
            }
        }
    }

    fun uploadImage(view: View) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), RC_PERMISSION_READ_EXTERNAL_STORAGE)
        } else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, RC_IMAGE_GALLERY)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == RC_PERMISSION_READ_EXTERNAL_STORAGE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, RC_IMAGE_GALLERY)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RC_IMAGE_GALLERY && resultCode == Activity.RESULT_OK) {
            val uri = data.data

            val storageRef = FirebaseStorage.getInstance().reference
            val imagesRef = storageRef.child("images")
            val userRef = imagesRef.child("teste")
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val filename = "teste_" + timeStamp
            val fileRef = userRef.child(filename)

            val uploadTask = fileRef.putFile(uri)
            uploadTask.addOnFailureListener { exception ->
                // Handle unsuccessful uploads
                Toast.makeText(this, "Upload failed!\n" + exception.message, Toast.LENGTH_LONG).show()
            }.addOnSuccessListener { taskSnapshot:UploadTask.TaskSnapshot ->
                //val downloadUrl = taskSnapshot.getDownloadUrl()
                Toast.makeText(this, "Upload finished!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
