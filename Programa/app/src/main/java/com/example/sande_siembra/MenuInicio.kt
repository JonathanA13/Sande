package com.example.sande_siembra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        button2.setOnClickListener{ mostrarDialogoBasico() }
    }

    private fun mostrarDialogoBasico() {
        val comprobacion = "Entra en el sí"
        val builder =
            AlertDialog.Builder(

                this
            )
        builder.setTitle("Registro de datos")
        builder.setMessage("¿Los datos se guardaron correctamente. ¿Desea Continuar ingresando datos?")
            .setPositiveButton("Sí") { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    "Guardando datos...",
                    Toast.LENGTH_SHORT
                ).show()
                Log.i("Pantalla", comprobacion)
            }
            .setNegativeButton(
                android.R.string.cancel
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Cancelando...", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
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