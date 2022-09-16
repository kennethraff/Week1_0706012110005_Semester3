package com.example.week1_0706012110005

import Database.GlobalVar
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week1_0706012110005.databinding.ActivityDataBinding
import com.example.week1_0706012110005.databinding.ActivityInputBinding
import model.Animal

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private lateinit var animal: Animal
    private var position = -1
    private var isCompleted =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        listener()
        GetIntent()
    }

    private fun listener() {
        binding.backButtonInputActivity.setOnClickListener() {
            val myIntent = Intent(this, DataActivity::class.java)
            startActivity(myIntent)
        }

        binding.simpanButtonInputActivity.setOnClickListener(){
            var nama = binding.namaHewanTextLayoutInputActivity .editText?.text.toString().trim()
            var jenis = binding.jenisHewanTextLayoutInputActivity.editText?.text.toString().trim()
            var usia = binding.usiaHewanTextLayoutInputActivity.editText?.text.toString().trim()

            animal = Animal(nama, jenis, usia)

            checker()




        }
    }

    private fun checker() {
        var isCompleted: Boolean = true
        if (animal.nama!!.isEmpty()) {
            binding.namaHewanTextLayoutInputActivity.error = "Nama harus diisi"
            isCompleted = false
        } else {
            binding.namaHewanTextLayoutInputActivity.error = ""
        }

        if (animal.jenis!!.isEmpty()) {
            binding.jenisHewanTextLayoutInputActivity.error = "Jenis harus diisi"
            isCompleted = false
        } else {
            binding.jenisHewanTextLayoutInputActivity.error = ""
        }

        if (animal.usia!!.isEmpty()) {
            binding.usiaHewanTextLayoutInputActivity.error = "Usia harus diisi 1-100"
            isCompleted = false
        } else if (animal.usia!!.contains(".*[A-Z].*".toRegex())) {
            binding.usiaHewanTextLayoutInputActivity.error = "usia tidak boleh ada huruf"
            isCompleted = false
        } else if (animal.usia!!.contains(".*[a-z].*".toRegex())) {
            binding.usiaHewanTextLayoutInputActivity.error = "usia tidak boleh ada huruf"
            isCompleted = false
        } else if (animal.usia!!.contains(".*[0-100].*".toRegex())) {
            if (animal.usia!!.toInt() > 100) {
                binding.usiaHewanTextLayoutInputActivity.error = "Rating harus 1-99 tidak boleh lebih dari 100"
                isCompleted = false
            } else {
                binding.usiaHewanTextLayoutInputActivity.error = ""
            }
        }

        if(isCompleted==true){
            if(position == -1){
                GlobalVar.listDataAnimal.add(animal)
                finish()
            }else{
                GlobalVar.listDataAnimal[position] = animal
                finish()
            }
        }


    }

    private fun Display(animal: Animal) {
        binding.namaHewanTextLayoutInputActivity.editText!!.setText(animal!!.nama)
        binding.jenisHewanTextLayoutInputActivity.editText!!.setText(animal!!.jenis)
        binding.usiaHewanTextLayoutInputActivity.editText!!.setText(animal!!.usia)

        binding.textView2.setText("Edit Hewan")


    }

    private fun GetIntent() {
        position = intent.getIntExtra("position", -1)
        if (position!=-1) {
            val animal = GlobalVar.listDataAnimal[position]
            Display(animal)
        }
    }


}