package com.example.sande_siembra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_2.setOnClickListener{ segunda() }
    }

    fun segunda(){
        val intentExplicito = Intent(
            this,
            MainActivity3::class.java
        )
        startActivity(intentExplicito)
    }
}