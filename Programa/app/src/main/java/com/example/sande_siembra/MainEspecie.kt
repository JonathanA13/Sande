package com.example.sande_siembra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_especie.*

class MainEspecie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_especie)

        btnCallas.setOnClickListener { Calla() }
        btnLirios.setOnClickListener { Lirios() }
        btnFlorVerano.setOnClickListener { FlorVerano() }

    }

    fun Calla(){

        val intentExplicito = Intent(this, MenuInicio::class.java)
        val dato = "CALLAS"
        intentExplicito.putExtra("especie", dato )
        startActivity(intentExplicito)



        /*val b: Bundle = Bundle()
        b.putString("espacieCalla", dato)
        intentExplicito.putExtras(b)
        startActivity(intentExplicito)*/



    }
    fun Lirios(){

        val intentExplicito = Intent(this, MenuInicio::class.java)
        val dato = "LIRIOS"
        intentExplicito.putExtra("especie", dato )
        startActivity(intentExplicito)


    }

    fun FlorVerano(){

        val intentExplicito = Intent(this, MenuInicio::class.java)
        val dato = "FLOR DE VERANO"
        intentExplicito.putExtra("especie", dato )
        startActivity(intentExplicito)


    }


}