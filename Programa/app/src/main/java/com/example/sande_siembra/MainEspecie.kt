package com.example.sande_siembra

import android.app.Service
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import kotlinx.android.synthetic.main.activity_main_especie.*


class MainEspecie : AppCompatActivity() {

    var context = this
    var connectivity : ConnectivityManager? = null
    var info : NetworkInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_especie)

        btnCallas.setOnClickListener {
            if (isConnected()){
                Calla()
            } else {
                Log.i("error", "No se tiene conexion a internet")
            }
        }
        btnLirios.setOnClickListener { Lirios() }
        btnFlorVerano.setOnClickListener { FlorVerano() }

    }

    fun isConnected() : Boolean {
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null){
            info = connectivity!!.activeNetworkInfo
            if (info != null){
                if(info!!.state == NetworkInfo.State.CONNECTED){
                    return true
                }
            }
        }
        return false
    }

    fun Calla(){

        val intentExplicito = Intent(
            this,
            MenuInicio::class.java)
        val dato = "CALLAS"
        intentExplicito.putExtra("especie", dato )
        Log.i("especie", "El nombre es: ${dato}")
        startActivity(intentExplicito)

    }
    fun Lirios(){

        val intentExplicito = Intent(this, MenuInicio::class.java)
        val datolirio = "LIRIOS"
        intentExplicito.putExtra("especie", datolirio )
        startActivity(intentExplicito)


    }

    fun FlorVerano(){

        val intentExplicito = Intent(this, MenuInicio::class.java)
        val datoflor = "FLOR DE VERANO"
        intentExplicito.putExtra("especie", datoflor )
        startActivity(intentExplicito)


    }


}