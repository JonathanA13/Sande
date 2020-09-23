package com.example.sande_siembra

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    //val calendar = Calendar.getInstance()
    //val currentDate: String =
        //DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.time)
    //val textViewDate: TextView = findViewById(R.id.textViewFecha)
    private val db = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db.firestoreSettings = settings
        //parametros()
        agregar1()
        agregar2()
        agregar3()
        val calendar = Calendar.getInstance()
        val currentDate: String =
            DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.time)
        val textViewDate: TextView = findViewById(R.id.textViewFecha)
        textViewDate.text = currentDate
        //Log.i("Eleccion: ", "fecha ${currentDate}")
        //val numeroSemana = calendar.get(calendar.weekYear)
        val numeroSemana = calendar[Calendar.WEEK_OF_YEAR]
        //Log.i("Eleccion: ", "semana ${numeroSemana}")
        txtViewNumeroSemana.text = numeroSemana.toString()
        //obtener()
        btnListo.setOnClickListener{ botonListo() }
        btn_buscar.setOnClickListener{ buscar() }

    }

    fun buscar(){
        /*var fechaRecuperacion = ""
        val valvula = editTxtValvula.text.toString().toInt()
        db.collection("Siembra").document(valvula.toString()).get().addOnSuccessListener {
            fechaRecuperacion = it.get("Fecha") as String
            Log.i("Recuperacion", fechaRecuperacion)
        }*/

        db.collection("Siembra")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.i("recuperacion1", "${document.id } "+ " => " +" ${document.data}")
                    }
                } else {
                    Log.i("recuperacion1", "Error getting documents: ", task.exception)
                }
            }
    }

    fun botonListo(){
        obtener()
        /*val intentExplicito = Intent(
            this,
            Registro::class.java
        )
        intentExplicito.putExtra("Fecha", )
        startActivity(intentExplicito)*/
    }

    fun obtener(){
        val calendar = Calendar.getInstance()
        val currentDate: String =
            DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.time)
        Log.i("Eleccion: ", "fecha ${currentDate}")
        val numeroSemana = calendar[Calendar.WEEK_OF_YEAR]
        Log.i("Eleccion: ", "semana ${numeroSemana}")
        val valvula = editTxtValvula.text.toString().toInt()
        //Log.i("Eleccion: ", valvula)
        val bloque = editTxtBloque.text.toString().toInt()
        //Log.i("Eleccion: ", bloque)
        val lado = cmbLado.selectedItem.toString()
        Log.i("Eleccion: ", lado)
        val etiqueta = cmbEtiqueta.selectedItem.toString()
        Log.i("Eleccion: ", etiqueta)
        val finca = cmbFinca.selectedItem.toString()
        var fincaNombre = ""
        if(finca.equals("Sande 2")){
            fincaNombre = "S2"
        } else {
            fincaNombre = "S4"
        }
        Log.i("Eleccion: ", fincaNombre)
        ServicioBDDMemoria.agregarCabecera(currentDate,numeroSemana,valvula,bloque,lado,etiqueta,fincaNombre)

        db.collection("Siembra").document(valvula.toString()).set(
            hashMapOf("Fecha" to currentDate,
                "Semana" to numeroSemana,
            "Bloque" to bloque,
            "Lado" to lado,
            "Etiqueta" to etiqueta,
            "Finca" to fincaNombre)
        )

        val intentExplicito = Intent(
            this,
            Registro::class.java
        )
        intentExplicito.putExtra("Fecha", currentDate )
        intentExplicito.putExtra("Semana", numeroSemana)
        intentExplicito.putExtra("Bloque", bloque)
        intentExplicito.putExtra("Valvula", valvula)
        startActivity(intentExplicito)

        /*val datosGuardarArchivo = Cabecera(currentDate,numeroSemana,valvula,bloque,lado,etiqueta)
        val archivo: File = File("datos//cabecera.xlsx")
        val ingreso = FileOutputStream(archivo,true)
        ingreso.bufferedWriter().use { out ->
            out.write("$datosGuardarArchivo")
        }*/
        //val internalStorageDir = filesDir
        //val cabecera = File(internalStorageDir, "cabecera.csv")

    }

    fun agregar1(){
        val etiquetas = arrayOf("Seleccionar","Norte","Sur")
        val spinner: Spinner = findViewById(R.id.cmbLado)
        val adapter: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size, etiquetas)
        spinner.setAdapter(adapter)
    }

    fun agregar2(){
        val etiquetas = arrayOf("Seleccionar","Flores","Bulbos")

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

    fun agregar3(){
        val finca = arrayOf("Seleccionar","Sande 2","Sande 4")
        val spinner: Spinner = findViewById(R.id.cmbFinca)
        val adapter: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size, finca)
        spinner.setAdapter(adapter)
    }



}
