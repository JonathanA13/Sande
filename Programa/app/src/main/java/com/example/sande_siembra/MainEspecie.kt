package com.example.sande_siembra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_especie.*

class MainEspecie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_especie)

        btnCallas.setOnClickListener { Calla() }




    }

    fun Calla(){

        val intentExplicito = Intent(
            this,
            MainActivity::class.java
        )


    }




}