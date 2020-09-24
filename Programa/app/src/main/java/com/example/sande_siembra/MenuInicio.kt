package com.example.sande_siembra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_menu_inicio.*

class MenuInicio : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_inicio)
        btnNuevo.setOnClickListener { botonNuevo() }
        btn_sincro.setOnClickListener{ sincronizar() }
    }
    fun botonNuevo(){

        val intentExplicito = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun sincronizar(){

        val fecha = "Sincronizacion"
        val bloque = "Sincronizacion"
        val lado = "Sincronizacion"
        val etiqueta = "Sincronizacion"
        val cama = "Sincronizacion"
        val procedimiento = "Sincronizacion"
        val variedad = "Sincronizacion"

        db.collection("SiembraPrueba").add(
            hashMapOf("Fecha" to fecha,
                "Bloque" to bloque,
                "Lado" to lado,
                "Etiqueta" to etiqueta
            )
        )

        db.collection("SiembraDatosPrueba").add(
            hashMapOf("Cama" to cama,
                "Variedad" to variedad,
                "Procedimiento" to procedimiento
                )
        )

    }
}