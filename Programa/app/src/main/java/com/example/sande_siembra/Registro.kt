package com.example.sande_siembra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val fecha = intent.getStringExtra("Fecha")
        val semana = intent.getIntExtra("Semana",0)
        val bloque = intent.getIntExtra("Bloque",0)
        val valvula = intent.getIntExtra("Valvula",0)

        txtFechaRegistro.text = fecha
        txtSemanaRegistro.text = semana.toString()
        txtViewBloqueRegistro.text = bloque.toString()
        txt_valvula.text = valvula.toString()
    }
}