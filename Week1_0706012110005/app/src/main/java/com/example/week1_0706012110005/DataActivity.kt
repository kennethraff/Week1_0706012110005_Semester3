package com.example.week1_0706012110005

import Adapter.ListAnimalRvAdapter
import Database.GlobalVar
import Interface.CardListener
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week1_0706012110005.databinding.ActivityDataBinding
import com.example.week1_0706012110005.databinding.ActivityMainBinding

class DataActivity : AppCompatActivity(),CardListener {
    private var adapter= ListAnimalRvAdapter(GlobalVar.listDataAnimal,this)
    private lateinit var binding: ActivityDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        listener()
        checkPermissions()
    }

    override fun onResume() {
        super.onResume()
        textHidden()
        setupRecyclerView()
    }

    private fun listener() {
        binding.addHewanFABDataActivity.setOnClickListener() {
            val myIntent = Intent(this, InputActivity::class.java)
            startActivity(myIntent);
        }
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        binding.listDataRV.layoutManager= layoutManager //Set layout
        binding.listDataRV.adapter=adapter //Set adapter
    }

    private fun textHidden(){
        if(GlobalVar.listDataAnimal.isEmpty()){
            binding.addHewanDataActivity.alpha = 1f
        } else {
            binding.addHewanDataActivity.alpha = 0f        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), GlobalVar.CAMERA_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Camera Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GlobalVar.CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == GlobalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCardClick(edit: Boolean, position: Int) {
        //kode untuk edit/delete
        if(edit == true){
            GlobalVar.listDataAnimal.removeAt(position)
            adapter.notifyDataSetChanged()
        }else{
            val intent = Intent(this, InputActivity::class.java).putExtra("position",position)
            startActivity(intent)
        }

    }


}