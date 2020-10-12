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
import kotlinx.android.synthetic.main.activity_main_especie.*
import java.io.File
import java.io.FileWriter


class MainEspecie : AppCompatActivity() {

    var context = this
    var connectivity : ConnectivityManager? = null
    var info : NetworkInfo? = null

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
        val carpeta = File(
            Environment.getExternalStorageDirectory().toString() + "/ExportarSQLiteCSV"
        )
        val archivoAgenda = "$carpeta/Usuarios.csv"
        var isCreate = false
        if (!carpeta.exists()) {
            isCreate = carpeta.mkdir()
        }
        try {
            val fileWriter = FileWriter(archivoAgenda)
            fileWriter.append("prueba1")
            fileWriter.append(",")
            fileWriter.append("prueba2")
            /*val admin = AdminSQLiteOpenHelper(this@MainActivity, "dbSistema", null, 1)
            val db: SQLiteDatabase = admin.getWritableDatabase()
            val fila = db.rawQuery("select * from usuarios", null)
            if (fila != null && fila.count != 0) {
                fila.moveToFirst()
                do {
                    fileWriter.append(fila.getString(0))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(1))
                    fileWriter.append(",")
                    fileWriter.append(fila.getString(2))
                    fileWriter.append("\n")
                } while (fila.moveToNext())
            } else {
                Toast.makeText(this@MainActivity, "No hay registros.", Toast.LENGTH_LONG).show()
            }
            db.close()*/
            fileWriter.close()
            Toast.makeText(
                this@MainEspecie,
                "SE CREO EL ARCHIVO CSV EXITOSAMENTE",
                Toast.LENGTH_LONG
            ).show()
        } catch (e: Exception) {
        }
    }






}