package com.example.sande_siembra

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sande_siembra.modelo.RegistroSiembra
import kotlinx.android.synthetic.main.activity_main_especie.*
import java.io.File
import java.io.FileWriter
import java.text.DateFormat
import java.util.*


class MainEspecie : AppCompatActivity() {

    var context = this
    var connectivity : ConnectivityManager? = null
    var info : NetworkInfo? = null
    var listaUsuarios: List<RegistroSiembra> = ArrayList<RegistroSiembra>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_especie)

        btnCallas.setOnClickListener {
            if (isConnected()){
                Calla()
            } else {
                Log.i("error", "No se tiene conexion a internet")
            }
        }
        pedirPermisos()
        btnLirios.setOnClickListener { Lirios() }
        btnFlorVerano.setOnClickListener { FlorVerano() }

    }

    fun isConnected() : Boolean {
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null){
            info = connectivity!!.activeNetworkInfo
            if (info != null){
                if(info!!.state == NetworkInfo.State.CONNECTED){
                    return true
                }
            }
        }
        return false
    }

    fun Calla(){

        val intentExplicito = Intent(
            this,
            MenuInicio::class.java)
        val dato = "CALLAS"
        intentExplicito.putExtra("especie", dato )
        Log.i("especie", "El nombre es: ${dato}")
        startActivity(intentExplicito)

    }
    fun Lirios(){


        exportarCSV()
        val intentExplicito = Intent(this, MenuInicio::class.java)
        val datolirio = "LIRIOS"
        intentExplicito.putExtra("especie", datolirio )
        startActivity(intentExplicito)

    }

    fun FlorVerano(){

        exportarCSVBDD()
        val intentExplicito = Intent(this, MenuInicio::class.java)
        val datoflor = "FLOR DE VERANO"
        intentExplicito.putExtra("especie", datoflor )
        startActivity(intentExplicito)


    }

    fun pedirPermisos() {
        // PERMISOS PARA ANDROID 6 O SUPERIOR
        if (ContextCompat.checkSelfPermission(
                this@MainEspecie,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainEspecie,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                0
            )
        }
    }


    fun exportarCSV() {
        val calendar = Calendar.getInstance()
        val currentDate: String =
            DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.time)
        val currentDate1: String =
            DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.time)
        Log.i("fecha", currentDate1)
        val fecha= currentDate
        val carpeta = File(
            Environment.getExternalStorageDirectory().toString() + "/ExportarSQLiteCSV"
        )
        val archivoAgenda = "$carpeta/Usuarios"+"$fecha"+".csv"

        var isCreate = false
        if (!carpeta.exists()) {
            isCreate = carpeta.mkdir()
        }
        try {
            val fileWriter = FileWriter(archivoAgenda)
            fileWriter.append("prueba1")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            fileWriter.append(",")
            fileWriter.append("prueba2")

            fileWriter.close()
            Toast.makeText(
                this@MainEspecie,
                "SE CREO EL ARCHIVO CSV EXITOSAMENTE",
                Toast.LENGTH_LONG
            ).show()
        } catch (e: Exception) {
        }
    }


    fun exportarCSVBDD() {
        val carpeta = File(
            Environment.getExternalStorageDirectory().toString() + "/ExportarSiembraSQLiteCSV"
        )
        val archivoAgenda = "$carpeta/SiembraRegistros.csv"
        var isCreate = false
        if (!carpeta.exists()) {
            isCreate = carpeta.mkdir()
        }
        try {
            val fileWriter = FileWriter(archivoAgenda)
            val admin =
                AdminSQLiteOpenHelper(this@MainEspecie, "SIEMBRA_BDD", null, 5)
            val db = admin.writableDatabase
            val fila = db.rawQuery("select * from SIEMBRA_TABLE", null)
            if (fila != null && fila.count != 0) {
                fila.moveToFirst()
                do {
                    fileWriter.append(fila.getString(0))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(1))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(2))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(3))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(4))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(5))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(6))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(7))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(8))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(9))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(10))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(11))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(12))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(13))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(14))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(15))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(16))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(17))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(18))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(19))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(20))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(21))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(22))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(23))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(24))


                    fileWriter.append("\n")
                } while (fila.moveToNext())
            } else {
                Toast.makeText(this@MainEspecie, "No hay registros.", Toast.LENGTH_LONG).show()
            }
            db.close()
            fileWriter.close()
            Toast.makeText(
                this@MainEspecie,
                "SE CREO EL ARCHIVO CSV EXITOSAMENTE",
                Toast.LENGTH_LONG
            ).show()
        } catch (e: java.lang.Exception) {
        }
    }






}