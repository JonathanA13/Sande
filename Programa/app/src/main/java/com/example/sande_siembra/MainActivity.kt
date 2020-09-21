package com.example.sande_siembra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*

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
        val etiquetas = arrayOf("Flores","Bulbos")

        val spinner: Spinner = findViewById(R.id.cmbLado)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.ComboLado,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    fun agregar2(){
        val etiquetas = arrayOf("Flores","Bulbos")

        val spinner: Spinner = findViewById(R.id.cmbEtiqueta)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.ComboEtiqueta,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }


}
