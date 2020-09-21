package com.example.sande_siembra

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //parametros()
        agregar1()
        agregar2()




    }

    /*fun parametros(){
        var lado: MutableList<String> = mutableListOf()
        lado.add("Norte")
        lado.add("Sur")

        var etiqueta: MutableList<String> = mutableListOf("")
        etiqueta.add("Flores")
        etiqueta.add("Bulbos")

    }*/

   

    fun agregar1(){
        val etiquetas = arrayOf("Norte","Sur")
        val spinner: Spinner = findViewById(R.id.cmbLado)
        val adapter: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size, etiquetas)
        spinner.setAdapter(adapter)

    }

    fun agregar2(){
        val etiquetas = arrayOf("Flores","Bulbos")

        val spinner: Spinner = findViewById(R.id.cmbEtiqueta)
        // Create an ArrayAdapter using the string array and a default spinner layout
        /*ArrayAdapter.createFromResource(
            this,
            R.array.ComboEtiqueta,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }*/

        val adapter: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size, etiquetas)
        spinner.setAdapter(adapter)

    }

    /*fun obtenerFechaActual(zonaHoraria: String?): String? {
        val formato = "yyyy-MM-dd"
        return obtenerFechaConFormato(formato, zonaHoraria)
    }*/


}
