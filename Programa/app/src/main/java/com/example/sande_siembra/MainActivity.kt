package com.example.sande_siembra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_llamar.setOnClickListener{ segunda() }
    }

    fun segunda(){
        val intentExplicito = Intent(
            this,
            MainActivity2::class.java
        )
        startActivity(intentExplicito)
    }
}
