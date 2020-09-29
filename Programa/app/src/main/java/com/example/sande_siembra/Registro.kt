package com.example.sande_siembra

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro.*


class Registro : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
        .build()


    var posicion = ""
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        db.firestoreSettings = settings

        val fecha = intent.getStringExtra("Fecha")
        val semana = intent.getIntExtra("Semana",0)
        val bloque = intent.getIntExtra("Bloque",0)
        val valvula = intent.getIntExtra("Valvula",0)
        val finca = intent.getStringExtra("Finca")
        val lado = intent.getStringExtra("Lado")
        val etiqueta = intent.getStringExtra("Etiqueta")


        txtFechaRegistro.text = fecha
        txtSemanaRegistro.text = semana.toString()
        txtViewBloqueRegistro.text = bloque.toString()
        txt_valvula.text = valvula.toString()
        txtViewFincaSiembra.text=finca.toString()
        val seleccionEtiqueta=etiqueta.toString()

        //LLENO CMBCALIBRE DE ACUERDO A SU ETIQUETA
        if(seleccionEtiqueta.equals("Flores") ){
            val calibre = arrayOf("9/12","12/15","15/18","18/20","20/26","26+")
            val spinner7: Spinner = findViewById(R.id.cmbCalibre)
            val adapterFlores: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, calibre)
            spinner7.setAdapter(adapterFlores)

        }//fin IF
        else{
            val calibreBulbos = arrayOf("0/4","4/6","6/9","9/12")
            val spinner77: Spinner = findViewById(R.id.cmbCalibre)
            val adapter7: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, calibreBulbos)
            spinner77.setAdapter(adapter7)

        }

        cmbCalibre.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {

                posicion = cmbCalibre.getItemAtPosition(position).toString()
                Log.i("Probar", posicion)
                /*if( posicion.equals("0/4") ){
                    txtViewBulbos.setText("Prueba de 0/4")
                } else if (posicion.equals("4/6")){
                    txtViewBulbos.setText("Prueba de 4/6")
                } else if (posicion.equals("6/9")){
                    txtViewBulbos.setText(" ")
                }*/
                txtViewBulbos.setText(posicion)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })






        editTxtMetros.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {

                if (s.toString().trim().isEmpty())
                {
                    txtViewBulbos.text="vacio"
                }
                else
                {

                    //txtViewBulbos.setText("hola")
                    //Log.i("Juntar", posicion)
                   Log.i("Juntar", posicion)

                    if(posicion.equals("0/4")){
                        Log.i("Juntar", "llega aqui")
                        txtViewBulbos.setText("prueba 0/4")


                    }
                    else{
                        Log.i("Juntar", "ingresa hasta aqui" + posicion)
                        txtViewBulbos.setText("otro")
                    }


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













        /*cmbCalibre.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
                txtViewBulbos.text="vacio calibre"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
                txtViewBulbos.text="lleno"
            }
        }*/





        definir()
        bulbos()


        //btnGuardar.setOnClickListener{ obtener(fecha, semana, bloque, valvula, finca, lado, etiqueta) }
        btnGuardar.setOnClickListener{ obtener() }


    }

    fun eleccionCalibre(prueba: String){

        Log.i("Probar", prueba)
        if(prueba.equals("null")){
            Log.i("Probar", prueba)
            txtViewBulbos.setText("Prueba de 4/6")
        }
    }


    fun bulbos()
    {
















    }








    fun definir(){
        val variedad = arrayOf("ACCENT", "ARANAL", "ASPEN", "AUCKLAND", "AVIGNON", "BLACK DIAMOND", "BLACK VELVET", "BUICK", "CAPTAIN ROMANCE", "CAVALESE",
            "CONFETTI", "CORDOBA", "COUPLET", "DENVER", "DIAMANTE", "E-026", "E-1129", "E-1153", "Z-1180", "E-166", "E-175", "E-233", "E-270", "E-336", "E-343", "E-364",
            "E-367", "E-369", "E-370", "E-371", "E-383", "E-434", "E-439", "E-494", "E-507", "E-512", "E-603", "E-605", "E-607", "E-613", "E-615",
            "E-616", "E-618", "E-624", "E-625", "E-626", "E-630", "E-632", "E-635", "E-636", "E-637", "E-703", "E-740", "EL CAPO", "ESCAPE", "FANTASIA",
            "GRAN PARADISO", "LA PAZ", "MANILA", "MEMORIES", "MOZART", "OLINA", "PAVIA", "PINK PANTHER", "RASPBERRY", "SAN REMO",
            "SCHWARZWALDER", "SHARP", "STRAUSS", "SUMATRA", "SUNRAY", "SUNSET", "SUNSHADE", "VERMEER", "Z-004", "Z-103", "Z-3117", "Z-371",
            "Z-199", "Z-957", "E-188", "E-353", "Z-947", "E-506", "E-632", "CAPT.3217", "CAPT.MORGAN", "CAPT.3421", "CAPT.ALMA"
        )
        val spinner1: Spinner = findViewById(R.id.cmbVariedad)
        val adapter1: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, variedad)
        spinner1.setAdapter(adapter1)

        val tipoSiembra = arrayOf("M.P1", "M.P2", "M.V1", "M.Z1", "M1",
            "M1.P1",
            "M1.P2",
            "M1.T1",
            "M1.T2",
            "M1.V1",
            "M1.Z1",
            "M1.Z2",
            "M2",
            "P1",
            "P1.T2",
            "P1.T3",
            "P1.Z2",
            "P2",
            "P3",
            "T1",
            "T2",
            "T3",
            "V0",
            "V0.T2",
            "V0.T3",
            "V1",
            "V1.T2",
            "V2",
            "V3",
            "Z",
            "Z1",
            "Z2",
            "Z3"
        )
        val spinner2: Spinner = findViewById(R.id.cmbTipoSiembra)
        val adapter2: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, tipoSiembra)
        spinner2.setAdapter(adapter2)

        val pl_proce = arrayOf("AF","AF-AF","af-af","HOLANDA","PL")
        val spinner3: Spinner = findViewById(R.id.cmbProce)
        val adapter3: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, pl_proce)
        spinner3.setAdapter(adapter3)

        val prueba1 = arrayOf("NINGUNA",
            "PRUEBA A",
            "PRUEBA B",
            "PRUEBA C",
            "PRUEBA D",
            "PRUEBA E",
            "PRUEBA FERTILIZACION VALVULA 4",
            "PRUEBA FERTILIZACION VALVULA 5",
            "TINAS")
        val spinner4: Spinner = findViewById(R.id.cmbPruebas1)
        val adapter4: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, prueba1)
        spinner4.setAdapter(adapter4)

        val prueba2 = arrayOf("NINGUNA",
            "ABIERTOS CON LA MANO",
            "BACTERIAS PLUS ORGANIC",
            "CRUZAMIENTOS",
            "MARCADOR 56",
            "MARCADOR 68",
            "PRUEBA DESINFECCION",
            "PRUEBA DOBLE AG3",
            "PRUEBA ONTSMET",
            "PRUEBA PRESIEMBRA ACTUAL",
            "PRUEBA PRESIEMBRA ACTUAL VAPORIZADA",
            "PRUEBA PRESIEMBRA ANTERIOR",
            "PRUEBA PRESIEMBRA ANTERIOR SIN VAPORIZAR",
            "SIN REVISAR ASIENTOS",
            "SIN VAPORIZAR",
            "TESTIGO",
            "TESTIGO DESINFECCION",
            "VAPORIZADA")
        val spinner5: Spinner = findViewById(R.id.cmbPruebas2)
        val adapter5: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, prueba2)
        spinner5.setAdapter(adapter5)

        val finca = arrayOf("S2", "S4")
        val spinner6: Spinner = findViewById(R.id.cmbFincaCabe)
        val adapter6: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, finca)
        spinner6.setAdapter(adapter6)









        val tamanioCama = arrayOf("0.9 mts","1.20 mts")
        val spinner8: Spinner = findViewById(R.id.cmbTamanioCama)
        val adapter8: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, tamanioCama)
        spinner8.setAdapter(adapter8)

        val Origen = arrayOf("NACIONAL","HOL-19","HOL-20", "HOLANDA")
        val spinner9: Spinner = findViewById(R.id.cmbOrigen)
        val adapter9: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, Origen)
        spinner9.setAdapter(adapter9)

        val Brote = arrayOf("Entero con brote","Brote grande","Brote pequeño")
        val spinner10: Spinner = findViewById(R.id.cmbBrote)
        val adapter10: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, Brote)
        spinner10.setAdapter(adapter10)

    }
    //fecha: String, semana: Int, bloque: Int, valvula: Int, finca: String, lado: String, etiqueta: String
    fun obtener(){

        val cama = editTxtCama.text.toString().toInt()
        val variedad = cmbVariedad.selectedItem.toString()
        val tipoSiembra = cmbTipoSiembra.selectedItem.toString()
        val procedimiento = cmbProce.selectedItem.toString()
        val prueba1 = cmbPruebas1.selectedItem.toString()
        val prueba2 = cmbPruebas2.selectedItem.toString()
        val fincaCabe = cmbFincaCabe.selectedItem.toString()
        val semanaCabe = editTxtSemanaCabe.text.toString().toInt()
        val bloqueCabe = editTxtBloqueCabe.text.toString().toInt()
        val metros = editTxtMetros.text.toString().toInt()
        val calibre = cmbCalibre.selectedItem.toString()
        val bulbos = 350

        /*db.collection("SiembraDatos").add(
            hashMapOf("Cama" to cama,
            "Variedad" to variedad,
            "tipoSiembra" to tipoSiembra,
            "Procedimiento" to procedimiento,
            "Prueba1" to prueba1,
            "Prueba2" to prueba2,
            "FincaCabe" to  fincaCabe,
            "SemanaCabe" to semanaCabe,
            "BloqueCabe" to  bloqueCabe,
            "Metros" to metros,
            "Calibre" to calibre,
            "Bulbos" to bulbos)
        )*/

        db.collection("SiembraDatosPrueba").add(
            hashMapOf("Cama" to cama,
                "Variedad" to variedad,
                "tipoSiembra" to tipoSiembra,
                "Procedimiento" to procedimiento,
                "Prueba1" to prueba1,
                "Prueba2" to prueba2,
                "FincaCabe" to  fincaCabe,
                "SemanaCabe" to semanaCabe,
                "BloqueCabe" to  bloqueCabe,
                "Metros" to metros,
                "Calibre" to calibre,
                "Bulbos" to bulbos)
        )

        /*db.collection("SiembraCompleta").add(
            hashMapOf("Fecha" to fecha,
                "Semana" to semana, "Finca" to finca, "Valvula" to valvula,
                "Bloque" to bloque,
                "Lado" to lado,
                "Etiqueta" to etiqueta,
                "Cama" to cama,
                "Variedad" to variedad,
                "tipoSiembra" to tipoSiembra,
                "Procedimiento" to procedimiento,
                "Prueba1" to prueba1,
                "Prueba2" to prueba2,
                "FincaCabe" to  finca,
                "SemanaCabe" to semana,
                "BloqueCabe" to  bloque,
                "Metros" to metros,
                "Calibre" to calibre,
                "Bulbos" to bulbos
            )
        )*/

        val toast = Toast.makeText(this, "DATO GUARDADO CORRECTAMENTE", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
        //limpiarCampos()

        //mostrarDialogoBasico()

        /*val builder = AlertDialog.Builder(this)
        builder.setMessage("Datos guardado correctamente")
            .setPositiveButton("Continuar",
                DialogInterface.OnClickListener { dialog, id ->
                    // FIRE ZE MISSILES!
                })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                })
        // Create the AlertDialog object and return it
        builder.create()*/

    }

    /*fun guardarGeneral(){

    }*/

    /*fun limpiarCampos(){
        editTxtCama.setText(" ")
        editTxtSemanaCabe.setText(" ")
        editTxtBloqueCabe.setText(" ")
        editTxtMetros.setText(" ")
        editTextNumber7.setText(" ")
        definir()
    }*/

    /*private fun mostrarDialogoBasico() {
        val builder =
            AlertDialog.Builder(
                this
            )
        builder.setTitle("Titulo")
        builder.setMessage("¿Quieres eliminar todos los datos?")
            .setPositiveButton("Sí") { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    "Eliminamos datos...",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton(
                R.string.cancel
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Cancel...", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }*/


    /*fun calcularBulbos(){
        editTxtMetros.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {

                if (s.toString().trim().isEmpty())
                {
                    txtViewBulbos.text="no mts"
                    btnGuardar.isEnabled=false
                }
                else
                {
                    if(cmbEtiqueta.selectedItem.toString()=="Flores")
                    {
                        if(txtViewFincaSiembra.toString()=="S4"){
                            if(cmbTamanioCama.selectedItem.toString()=="1.20 mts"){
                                if (cmbCalibre.selectedItem.toString()=="9/12"){
                                    val calcular=editTxtMetros.text.toString().toDouble()*28
                                    txtViewBulbos.text=calcular.toString()
                                }
                                else {
                                    if(cmbCalibre.selectedItem.toString()=="12/15"){
                                        val calcular=editTxtMetros.text.toString().toDouble()*20
                                        txtViewBulbos.text=calcular.toString()
                                    }
                                    else{
                                        if(cmbCalibre.selectedItem.toString()=="15/18"){
                                            val calcular=editTxtMetros.text.toString().toDouble()*16
                                            txtViewBulbos.text=calcular.toString()
                                        }
                                        else{
                                            if(cmbCalibre.selectedItem.toString()=="18/20"){
                                                val calcular=editTxtMetros.text.toString().toDouble()*12
                                                txtViewBulbos.text=calcular.toString()
                                            }
                                            else{
                                                if(cmbCalibre.selectedItem.toString()=="22/26"){
                                                    val calcular=editTxtMetros.text.toString().toDouble()*9
                                                    txtViewBulbos.text=calcular.toString()
                                                }
                                                else{
                                                    if(cmbCalibre.selectedItem.toString()=="26+"){
                                                        val calcular=editTxtMetros.text.toString().toDouble()*6
                                                        txtViewBulbos.text=calcular.toString()
                                                    }
                                                    else{

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else{
//si es de 1.90 la cama
                                if(cmbTamanioCama.selectedItem.toString()=="0.9 mts"){
                                    if (cmbCalibre.selectedItem.toString()=="9/12"){
                                        val calcular=editTxtMetros.text.toString().toDouble()*22
                                        txtViewBulbos.text=calcular.toString()
                                    }
                                    else {
                                        if(cmbCalibre.selectedItem.toString()=="12/15"){
                                            val calcular=editTxtMetros.text.toString().toDouble()*16
                                            txtViewBulbos.text=calcular.toString()
                                        }
                                        else{
                                            if(cmbCalibre.selectedItem.toString()=="15/18"){
                                                val calcular=editTxtMetros.text.toString().toDouble()*13
                                                txtViewBulbos.text=calcular.toString()
                                            }
                                            else{
                                                if(cmbCalibre.selectedItem.toString()=="18/20"){
                                                    val calcular=editTxtMetros.text.toString().toDouble()*10
                                                    txtViewBulbos.text=calcular.toString()
                                                }
                                                else{
                                                    if(cmbCalibre.selectedItem.toString()=="22/26"){
                                                        val calcular=editTxtMetros.text.toString().toDouble()*7
                                                        txtViewBulbos.text=calcular.toString()
                                                    }
                                                    else{
                                                        if(cmbCalibre.selectedItem.toString()=="26+"){
                                                            val calcular=editTxtMetros.text.toString().toDouble()*5
                                                            txtViewBulbos.text=calcular.toString()
                                                        }
                                                        else{
//si no hay calibre

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }// fin de cama 1.90
                                else{}
                            }

//sande 4 fin
                        }
                        else{
//sande 2
                            if(txtViewFincaSiembra.toString()=="S2"){
                                if(cmbTamanioCama.selectedItem.toString()=="1.20 mts"){
                                    if (cmbCalibre.selectedItem.toString()=="9/12"){
                                        val calcular=editTxtMetros.text.toString().toDouble()*44
                                        txtViewBulbos.text=calcular.toString()
                                    }
                                    else {
                                        if(cmbCalibre.selectedItem.toString()=="12/15"){
                                            val calcular=editTxtMetros.text.toString().toDouble()*28
                                            txtViewBulbos.text=calcular.toString()
                                        }
                                        else{
                                            if(cmbCalibre.selectedItem.toString()=="15/18"){
                                                val calcular=editTxtMetros.text.toString().toDouble()*20
                                                txtViewBulbos.text=calcular.toString()
                                            }
                                            else{
                                                if(cmbCalibre.selectedItem.toString()=="18/20"){
                                                    val calcular=editTxtMetros.text.toString().toDouble()*16
                                                    txtViewBulbos.text=calcular.toString()
                                                }
                                                else{
                                                    if(cmbCalibre.selectedItem.toString()=="22/26"){
                                                        val calcular=editTxtMetros.text.toString().toDouble()*9
                                                        txtViewBulbos.text=calcular.toString()
                                                    }
                                                    else{
                                                        if(cmbCalibre.selectedItem.toString()=="26+"){
                                                            val calcular=editTxtMetros.text.toString().toDouble()*9
                                                            txtViewBulbos.text=calcular.toString()
                                                        }
                                                        else{

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                else{
//si es de 1.90 la cama
                                    if(cmbTamanioCama.selectedItem.toString()=="0.9 mts"){
                                        if (cmbCalibre.selectedItem.toString()=="9/12"){
                                            val calcular=editTxtMetros.text.toString().toDouble()*35
                                            txtViewBulbos.text=calcular.toString()
                                        }
                                        else {
                                            if(cmbCalibre.selectedItem.toString()=="12/15"){
                                                val calcular=editTxtMetros.text.toString().toDouble()*22
                                                txtViewBulbos.text=calcular.toString()
                                            }
                                            else{
                                                if(cmbCalibre.selectedItem.toString()=="15/18"){
                                                    val calcular=editTxtMetros.text.toString().toDouble()*16
                                                    txtViewBulbos.text=calcular.toString()
                                                }
                                                else{
                                                    if(cmbCalibre.selectedItem.toString()=="18/20"){
                                                        val calcular=editTxtMetros.text.toString().toDouble()*13
                                                        txtViewBulbos.text=calcular.toString()
                                                    }
                                                    else{
                                                        if(cmbCalibre.selectedItem.toString()=="22/26"){
                                                            val calcular=editTxtMetros.text.toString().toDouble()*7
                                                            txtViewBulbos.text=calcular.toString()
                                                        }
                                                        else{
                                                            if(cmbCalibre.selectedItem.toString()=="26+"){
                                                                val calcular=editTxtMetros.text.toString().toDouble()*5
                                                                txtViewBulbos.text=calcular.toString()
                                                            }
                                                            else{
//si no hay calibre

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }// fin de cama 1.90
                                    else{}
                                }
                            }
                            else{}

                        }// fin si sande 2
                    }//fin si es Etiqueta Flores



                    else{ //si es bulbos

                        if(cmbCalibre.selectedItem.toString()=="0/4")
                        {
                            val calcular=editTxtMetros.text.toString().toDouble()*200
                            txtViewBulbos.text=calcular.toString()
                        }
                        else{
                            if(cmbCalibre.selectedItem.toString()=="4/6"){
                                val calcular=editTxtMetros.text.toString().toDouble()*140
                                txtViewBulbos.text=calcular.toString()
                            }
                            else{
                                if(cmbCalibre.selectedItem.toString()=="6/9")
                                {
                                    val calcular=editTxtMetros.text.toString().toDouble()*60
                                    txtViewBulbos.text=calcular.toString()
                                }
                                else
                                    if(cmbCalibre.selectedItem.toString()=="6/9")
                                    {
                                        val calcular=editTxtMetros.text.toString().toDouble()*48
                                        txtViewBulbos.text=calcular.toString()
                                    }
                                    else{}
                            }
                        }
                    } // fin si es bulbos















                }//fin si mts no es vacio
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int,
                                           after:Int) {
// TODO Auto-generated method stub
            }
            override fun afterTextChanged(s: Editable) {
// TODO Auto-generated method stub
            }
        })

    }*/





}