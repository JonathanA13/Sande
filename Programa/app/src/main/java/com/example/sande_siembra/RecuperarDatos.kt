package com.example.sande_siembra

import android.os.Bundle
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity


class RecuperarDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_datos)

        val tabla = TablaDinamica(this, findViewById(R.id.tabla) as TableLayout)
        tabla.agregarCabecera(R.array.cabecera_tabla)
        for (i in 0..14) {
            val elementos = ArrayList<String>()
            elementos.add(Integer.toString(i))
            elementos.add("Casilla [$i, 0]")
            elementos.add("Casilla [$i, 1]")
            elementos.add("Casilla [$i, 2]")
            elementos.add("Casilla [$i, 3]")
            tabla.agregarFilaTabla(elementos)
        }

    }
}