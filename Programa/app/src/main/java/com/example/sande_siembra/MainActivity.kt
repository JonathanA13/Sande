package com.example.sande_siembra

import android.R.layout
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.*

import androidx.appcompat.app.AppCompatActivity
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







        editTxtValvula.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {

                if (s.toString().trim().isEmpty())
                {
                    btnListo.isEnabled=false
                }
                else
                {
                    btnListo.setEnabled(true)
                    btnListo.isEnabled=true
                }
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int,
                                           after:Int) {
                // TODO Auto-generated method stub
            }
            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })

        editTxtBloque.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {

                if (s.toString().trim().isEmpty())
                {
                    btnListo.isEnabled=false
                }
                else
                {
                    btnListo.setEnabled(true)
                    btnListo.isEnabled=true
                }
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int,
                                           after:Int) {
                // TODO Auto-generated method stub
            }
            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })


























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


        btnListo.setOnClickListener{ obtener() }

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
                        Log.i("valores ", "${document.getData().values}")
                        document.getData().values.forEach {
                            Log.i("valorsitos", "${it}")
                        }
                    }
                } else {
                    Log.i("recuperacion1", "Error getting documents: ", task.exception)
                }
            }
    }







    fun botonListo(){


        if(editTxtValvula.text.toString().trim().isEmpty())
        {
            val toast = Toast.makeText(this, "VALVULA NO PUEDE ESTAR VACIO", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.show()
            Log.i("Medida","campo vacio valvula")
        }
        else {

            if(editTxtBloque.text.toString().trim().isEmpty())
            {
                val toast = Toast.makeText(this, "BLOQUE NO PUEDE ESTAR VACIO", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                toast.show()
                Log.i("Medida","campo bloque vacio")
            }
            else {
                Log.i("Medida","campo bloque lleno")

            }
            Log.i("Medida","campo lleno valvula")



        }



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
        Log.i("Eleccion: ", valvula.toString())
        /*if(editTxtValvula.text.toString().trim().isEmpty()){

            val toast = Toast.makeText(this, "Mensaje 2", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.show()
            Log.i("Medida","campo vacio")
        }*/
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

        /*db.collection("Siembra").document(valvula.toString()).set(
            hashMapOf("Fecha" to currentDate,
                "Semana" to numeroSemana,
            "Bloque" to bloque,
            "Lado" to lado,
            "Etiqueta" to etiqueta,
            "Finca" to fincaNombre)
        )*/

        /*db.collection("Siembra").add(
            hashMapOf("Fecha" to currentDate,
            "Semana" to numeroSemana, "Finca" to fincaNombre, "Valvula" to valvula,
            "Bloque" to bloque,
            "Lado" to lado,
            "Etiqueta" to etiqueta
            )
        )*/

        db.collection("SiembraPrueba").add(
            hashMapOf("Fecha" to currentDate,
                "Semana" to numeroSemana, "Finca" to fincaNombre, "Valvula" to valvula,
                "Bloque" to bloque,
                "Lado" to lado,
                "Etiqueta" to etiqueta
            )
        )

        val intentExplicito = Intent(
            this,
            Registro::class.java
        )
        intentExplicito.putExtra("Fecha", currentDate )
        intentExplicito.putExtra("Semana", numeroSemana)
        intentExplicito.putExtra("Bloque", bloque)
        intentExplicito.putExtra("Valvula", valvula)
        intentExplicito.putExtra("Finca", fincaNombre)
        intentExplicito.putExtra("Lado", lado)
        intentExplicito.putExtra("Etiqueta", etiqueta)
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
        val etiquetas = arrayOf("Ninguno","Norte","Sur")
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

    fun agregar3(){
        val finca = arrayOf("Seleccionar","Sande 2","Sande 4")
        val spinner: Spinner = findViewById(R.id.cmbFinca)
        val adapter: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size, finca)
        spinner.setAdapter(adapter)
    }



}
