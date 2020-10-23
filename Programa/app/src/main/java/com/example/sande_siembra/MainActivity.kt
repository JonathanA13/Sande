package com.example.sande_siembra

import android.R.layout
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.Gravity

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.*
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.io.File
import java.text.DateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    val calendar = Calendar.getInstance()
    val currentDate: String =
        DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.time)
    var numeroSemana = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewDate: TextView = findViewById(R.id.textViewFecha)
        textViewDate.text = currentDate
        numeroSemana = calendar[Calendar.WEEK_OF_YEAR]
        txtViewNumeroSemana.text = numeroSemana.toString()

        val especie = intent.getStringExtra("especie")
        tvEspecie.text= especie

        //parametros()
        agregar1()
        agregar2()
        agregar3()

        btnListo.setOnClickListener{ obtener_Datos() }

    }


    fun agregar1(){
        val etiquetas = arrayOf("Ninguno","Norte","Sur")
        val spinner: Spinner = findViewById(R.id.cmbLado)
        val adapter: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size, etiquetas)
        spinner.setAdapter(adapter)
    }

    fun agregar2(){
        val etiquetas = arrayOf("Seleccionar", "Flores","Bulbos")
        val spinner: Spinner = findViewById(R.id.cmbEtiqueta)
        val adapter: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size, etiquetas)
        spinner.setAdapter(adapter)

    }

    fun agregar3(){
        val finca = arrayOf("Seleccionar","Sande 2","Sande 4")
        val spinner: Spinner = findViewById(R.id.cmbFinca)
        val adapter: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size, finca)
        spinner.setAdapter(adapter)
    }


    fun obtener_Datos() {

        if (cmbFinca.selectedItem.equals("Seleccionar") || editTxtValvula.text.length == 0 ||
            editTxtBloque.text.length == 0|| cmbEtiqueta.selectedItem.equals("Seleccionar") ){
            if (cmbFinca.selectedItem.equals("Seleccionar")){
                validaciones_Campos_Vacíos("FINCA")
            } else if (editTxtValvula.text.length == 0){
                validaciones_Campos_Vacíos("VALVULA")
            } else if (editTxtBloque.text.length == 0){
                validaciones_Campos_Vacíos("BLOQUE")
            } else if (cmbEtiqueta.selectedItem.equals("Seleccionar")){
                validaciones_Campos_Vacíos("ETIQUETA")
            }


        } else {

            val especie = tvEspecie.text.toString()
            val finca = cmbFinca.selectedItem.toString()
            var fincaNombre = ""
            if(finca.equals("Sande 2")){
                fincaNombre = "S2"
            } else {
                fincaNombre = "S4"
            }
            val valvula = editTxtValvula.text.toString().toInt()
            val bloque = editTxtBloque.text.toString().toInt()
            val lado = cmbLado.selectedItem.toString()
            val etiqueta = cmbEtiqueta.selectedItem.toString()
            //Log.i("Eleccion: ", etiqueta)
            //ServicioBDDMemoria.agregarCabecera(currentDate,numeroSemana,valvula,bloque,lado,etiqueta,fincaNombre)
            val intentExplicito = Intent(
                this,
                Registro::class.java
            )
            //intentExplicito.putExtra("ID", numeroID)
            intentExplicito.putExtra("Fecha", currentDate )
            intentExplicito.putExtra("Semana", numeroSemana)
            intentExplicito.putExtra("Especie", especie)
            intentExplicito.putExtra("Bloque", bloque)
            intentExplicito.putExtra("Valvula", valvula)
            intentExplicito.putExtra("Finca", fincaNombre)
            intentExplicito.putExtra("Lado", lado)
            intentExplicito.putExtra("Etiqueta", etiqueta)
            startActivity(intentExplicito)

        }
    }

    fun validaciones_Campos_Vacíos(mensaje: String){
        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
        with(builder)
        {
            setTitle("MENSAJE DE ERROR")
            setMessage("EL CAMPO ${mensaje} ESTÁ VACÍO")
            builder.setPositiveButton("OK") { dialogInterface, i ->
                Log.i("Pantalla", "aceptar")
            }
            show()
        }
    }

}


// ************************************************* FUNCIONES COMENTADAS ***********************************************************

/* fun obtener(){

        if(cmbFinca.selectedItemPosition==0){
            val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
            with(builder)
            {
                setTitle("ERROR")
                setMessage("Escoja una FINCA")
                builder.setPositiveButton("OK") { dialogInterface, i ->
                    Log.i("Pantalla", "aceptar")
                }
                show()
            }
        } else{
            if(editTxtValvula.text.toString().equals("")){
                val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
                with(builder)
                {
                    setTitle("ERROR")
                    setMessage("Ingrese la VÁLVULA")
                    builder.setPositiveButton("OK") { dialogInterface, i ->
                        Log.i("Pantalla", "aceptar")
                    }
                    show()
                }

            } else{
                if(editTxtBloque.text.toString().equals("")){
                    val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
                    with(builder)
                    {
                        setTitle("ERROR")
                        setMessage("Ingrese un BLOQUE")
                        builder.setPositiveButton("OK") { dialogInterface, i ->
                            Log.i("Pantalla", "aceptar")
                        }
                        show()
                    }
                }
                else{
                    if(cmbEtiqueta.selectedItemPosition==0){
                        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
                        with(builder)
                        {
                            setTitle("ERROR")
                            setMessage("Escoja una ETIQUETA")
                            builder.setPositiveButton("OK") { dialogInterface, i ->
                                Log.i("Pantalla", "aceptar")
                            }
                            show()
                        }
                    }

                    else{ //CAMPOS LLENOS
                        Log.i("Verificar", "LLega hasta aquí")

                        val calendar = Calendar.getInstance()
                        val currentDate: String =
                            DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.time)
                        Log.i("Eleccion: ", "fecha ${currentDate}")
                        val numeroSemana = calendar[Calendar.WEEK_OF_YEAR]
                        Log.i("Eleccion: ", "semana ${numeroSemana}")
                        val valvula = editTxtValvula.text.toString().toInt()
                        val especie = tvEspecie.text.toString()

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


                        val intentExplicito = Intent(
                            this,
                            Registro::class.java
                        )
                        //intentExplicito.putExtra("ID", numeroID)
                        intentExplicito.putExtra("Fecha", currentDate )
                        intentExplicito.putExtra("Semana", numeroSemana)
                        intentExplicito.putExtra("Especie", especie)
                        intentExplicito.putExtra("Bloque", bloque)
                        intentExplicito.putExtra("Valvula", valvula)
                        intentExplicito.putExtra("Finca", fincaNombre)
                        intentExplicito.putExtra("Lado", lado)
                        intentExplicito.putExtra("Etiqueta", etiqueta)
                        startActivity(intentExplicito)

                    }

                }//fin else

            }//fin else campo  vacio

        }

    }*/


/*fun buscar(){
        /*var fechaRecuperacion = ""
        val valvula = editTxtValvula.text.toString().toInt()
        db.collection("Siembra").document(valvula.toString()).get().addOnSuccessListener {
            fechaRecuperacion = it.get("Fecha") as String
            Log.i("Recuperacion", fechaRecuperacion)
        }*/

        db.collection("SiembraSprint")
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
    }*/



/*fun leerRegistrosCSV(){
    val file = File("/sdcard/ExportarDatosCSV/DatosSiembra.csv")
    val lines: List<String> = file.readLines()
    //Log.i("Fechita", "El tamaño es: ${lines.size}")
    lines.forEachIndexed { index, s ->
        val line = lines[index]
        //Log.i("Fechita", "La fecha es: ${line}")
        val tokens = line.split(",")
        val fecha = tokens[0]
        val cama = tokens[1]
        val prueba1 = tokens[2]
        val prueba2 = tokens[3]
        val origen = tokens[4]
        val variedad = tokens[5]
        val tipoSiembra = tokens[6]
        val fincaGeneral1 = tokens[7]
        val bloqueGeneral1 = tokens[8]
        val tipoSiembra1 = tokens[9]
        val procedimiento = tokens[10]
        val calibre = tokens[11]
        val semanaGeneral1 = tokens[12]
        val metros = tokens[13]
        val bulbos = tokens[14]
        val semanaCabe = tokens[15]
        val bloqueCabe = tokens[16]
        val fincaCabe = tokens[17]
        val tamanioCama = tokens[18]
        val brote = tokens[19]
        val otraPrueba = tokens[20]
        val valvulaGeneral = tokens[21]
        val ladoGeneral1 = tokens[22]
        val etiquetaGeneral1 = tokens[23]

        Log.i("Fechita", "La fecha en registro es: ${fecha}")
        Log.i("Fechita", "La semana en registro es: ${prueba1}")

        ServicioBDDMemoria.agregarListaDatosSiembra(
            cama.toInt(), variedad, tipoSiembra, procedimiento, prueba1, prueba2,
            fincaCabe, semanaCabe.toInt(), bloqueCabe.toInt(), metros.toInt(), calibre, bulbos.toInt(),
            tamanioCama, brote, origen, otraPrueba, fecha, semanaGeneral1.toInt(),
            fincaGeneral1, valvulaGeneral.toInt(), bloqueGeneral1.toInt(), ladoGeneral1, etiquetaGeneral1
        )
    }


}*/



/*fun verificar_id() {
    var contadorSecundario1 = 0
    var numeros = arrayListOf<Int>()
    db.collection("Prueba").get().addOnSuccessListener { resultado ->
        for (documento in resultado){
            numeros.add(documento.id.toInt())
            numeros.sort()
            Log.i("recibir", "La lista es: ${numeros}")
            val ultimo = numeros.last()
            Log.i("recibir", "Este es el ultimo número: ${ultimo}")
            //val idBase = documento.id.toInt()
            //Log.i("recibir","El ******************* id de la base es: ${idBase}")
            contadorSecundario1 = ultimo + 1
            //Log.i("recibir", "El ID es: ${contadorSecundario1}")
        }
        contador = contadorSecundario1
        Log.i("recibir", "El ID que se va a guardar es: ${contador}")
    }

    /*db.collection("Prueba").get().addOnCompleteListener { resultado ->
            if(resultado.isSuccessful){
                for(documento in resultado.result!!){
                    var id_guardado = documento.id.toInt()
                    contadorSecundario = id_guardado + 1
                    contador = contadorSecundario
                    Log.i("recibir", "El contador es: ${contador}")
                }
                recepcionID(contador)

            } else {
                id_guardar = 0
                Log.i("recibir","Entra aqui **********************")
            }
        }*/

    /*db.collection("Prueba").get().addOnSuccessListener { resultado ->
        if (resultado.size() >= 0) {
            //val ver = resultado.size()
            Log.i("recibir", "El resultado es: ${resultado.size()}")
            for (documento in resultado) {
                //documento.id.toInt()
                var id_guardado = documento.id.toInt()
                Log.i("recibir", "El id de la base es: ${id_guardado}")
                contadorSecundario1 = id_guardado + 1
                //contador = contadorSecundario
                Log.i("recibir", "El contador es: ${contador}")
            }
            contador = contadorSecundario1
            Log.i("recibir", "El contador 1 es: ${contador}")
            recepcionID(contador)
        } else {
            id_guardar = 0
            Log.i("recibir", "Entra aqui **********************")
        }*/



    /* db.collection("Prueba").get().addOnSuccessListener { resultado ->
     if(resultado.size() >= 0){
         for(documento in resultado){
             documento.id.toInt()
             var id_guardado = documento.id.toInt()
             contadorSecundario = id_guardado + 1
             //contador = contadorSecundario
             Log.i("recibir", "El contador es: ${contador}")
         }
         contador = contadorSecundario
         Log.i("recibir", "El contador 1 es: ${contador}")
     } else {
         id_guardar = 0
         Log.i("recibir","Entra aqui **********************")
     }
     contador = contadorSecundario
     Log.i("recibir", "El contador 2 es: ${contador}")
     contador = recepcionID(contadorSecundario)
 }
 Log.i("recibir","El contador que se envia 1 es: ${contador}")
 //if (contador!=0){
 //} else {
   //  verificar_id()
 //}
 return recepcionID(contadorSecundario)*/
}*/


// ***************************************************** ESTO ESTABA DENTRO DEL ON CREATE ***********************************************

/*
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


 */

// *********************************************************** ESTAS SON VARIABLES GLOBALES **********************************************

//val calendar = Calendar.getInstance()
//val currentDate: String =
//DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.time)
//val textViewDate: TextView = findViewById(R.id.textViewFecha)
//private val db = FirebaseFirestore.getInstance()
/*val settings = FirebaseFirestoreSettings.Builder()
    .setPersistenceEnabled(true)
    .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
    .build()*/


// ******************************************************** FUNCIONES **************************************************

/*

    fun recepcionID(idAgregar: Int) {
        Log.i("recibir","El contador que se envia es: ${idAgregar}")
        //contador = idAgregar
        contadorSecundario = idAgregar
        Log.i("recibir","El contador secundario es: ${contadorSecundario}")
        //return contador
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
 */

// **************************************************** DENTRO DEL MÉTODO OBTENER ***********************************************

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
/*db.collection("SiembraSprint").add(
    hashMapOf("Fecha" to currentDate,
        "Semana" to numeroSemana, "Finca" to fincaNombre, "Valvula" to valvula,
        "Bloque" to bloque,
        "Lado" to lado,
        "Etiqueta" to etiqueta
    )
)*/

//var idGuardarFirestore = verificar_id()
/*verificar_id()
val numeroID = contador

Log.i("rece", "El id que se recibe es: ${numeroID}")

/*db.collection("Prueba").add(
    hashMapOf("Fecha" to currentDate,
    "Semana" to numeroSemana, "Finca" to fincaNombre, "Valvula" to valvula,
    "Bloque" to bloque,
    "Lado" to lado,
    "Etiqueta" to etiqueta
))*/

//val id_base_datos = 0

db.collection("Prueba").document("${numeroID}").set(
    hashMapOf("Fecha" to currentDate,

        "Semana" to numeroSemana, "Finca" to fincaNombre, "Valvula" to valvula,
        "Bloque" to bloque,
        "Lado" to lado,
        "Etiqueta" to etiqueta
    ))

    "Semana" to numeroSemana, "Finca" to fincaNombre, "Valvula" to valvula,
    "Bloque" to bloque,
    "Lado" to lado,
    "Etiqueta" to etiqueta
))*/

/*
/*val datosGuardarArchivo = Cabecera(currentDate,numeroSemana,valvula,bloque,lado,etiqueta)
                        val archivo: File = File("datos//cabecera.xlsx")
                        val ingreso = FileOutputStream(archivo,true)
                        ingreso.bufferedWriter().use { out ->
                            out.write("$datosGuardarArchivo")
                        }*/
                        //val internalStorageDir = filesDir
                        //val cabecera = File(internalStorageDir, "cabecera.csv")
 */


// ************************************************** CÓDIGO DENTRO DE AGREGAR 2 **************************************************

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


/*var posicion = ""
    var id_guardar = 0
    var contador = 0
    var contadorSecundario = 0*/