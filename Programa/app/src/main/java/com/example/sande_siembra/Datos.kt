package com.example.sande_siembra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sande_siembra.modelo.DatosSiembra
import com.example.sande_siembra.modelo.Siembra
import kotlinx.android.synthetic.main.activity_datos.*
import java.io.File
import java.util.EnumSet.of
import java.util.List.of

class Datos : AppCompatActivity(), RecyclerAdapter.OnDatosSiembraClickListener {

    var listaDatosSiembra = arrayListOf<DatosSiembra>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        /*val listaDatosSiembra = listOf(
            DatosSiembra(
                10, "Vermer", "Vermer", "Vermer",
                "Vermer", "Vermer", "Vermer", 15, 20, 25, "Vermer",
                15, "Vermer", "Vermer", "Vermer", "Vermer", "12 Oct 2020", 15,
                "Vermer", 12, 5, "Vermer", "Vermer"
            )
        )*/
        leerRegistrosCSV()
        Log.i("listita", "la lista es: ${listaDatosSiembra}")
        Log.i("listita", "la cantidad de registros de lista es: ${listaDatosSiembra.size}")
        recyclerView.adapter = RecyclerAdapter(this, listaDatosSiembra, this)
    }

    fun leerRegistrosCSV(){
        val file = File("/sdcard/ExportarDatosCSV/DatosSiembra4.csv")
        //val file = File("/sdcard/Download/DatosSiembra2.csv")
        // /sdcard/Download/DatosSiembra2.csv
        val lines: List<String> = file.readLines()
        //Log.i("Fechita", "El tamaño es: ${lines.size}")
        lines.forEachIndexed { index, s ->
            val line = lines[index]
            Log.i("Fechita", "El índice es: ${index}")
            Log.i("Fechita", "La fecha es: ${line}")
            val tokens = line.split(",")
            val fecha = tokens[0]
            val cama = tokens[1]
            val prueba1 = tokens[2]
            val prueba2 = tokens[3]
            val origen = tokens[4]
            val variedad = tokens[5]
            val fincaGeneral1 = tokens[6]
            val bloqueGeneral1 = tokens[7]
            val tipoSiembra = tokens[8]
            //val tipoSiembra1 = tokens[9]
            val procedimiento = tokens[9]
            val calibre = tokens[10]
            val semanaGeneral1 = tokens[11]
            val metros = tokens[12]
            val bulbos = tokens[13]
            val semanaCabe = tokens[14]
            val bloqueCabe = tokens[15]
            val fincaCabe = tokens[16]
            val tamanioCama = tokens[17]
            val brote = tokens[18]
            val otraPrueba = tokens[19]
            val valvulaGeneral = tokens[20]
            val ladoGeneral1 = tokens[21]
            val etiquetaGeneral1 = tokens[22]

            Log.i("Fechita", "La fecha es: ${fecha}")
            Log.i("Fechita", "La semana es: ${prueba1}")
            Log.i("Fechita", "La semana es: ${origen}")
            Log.i("Fechita", "La semana es: ${variedad}")
            Log.i("Fechita", "La semana es: ${tipoSiembra}")
            Log.i("Fechita", "La semana es: ${valvulaGeneral}")
            Log.i("Fechita", "La semana es: ${bloqueGeneral1}")

            /*
            fechaGeneral,cama,prueba1,prueba2,origen,variedad,fincaGeneral,
            bloqueGeneral,tipoSiembra,procedimiento,calibre,semanaGeneral,
            metros, bulbos, semanaCabe, bloqueCabe, fincaCabe, tamanioCama, brote,
            otraPrueba, valvulaGeneral, ladoGeneral,etiquetaGeneral
            */
            if ( index != 0) {
                listaDatosSiembra.add(
                    DatosSiembra(
                        fecha,
                        cama.toInt(),
                        prueba1,
                        prueba2,
                        origen,
                        variedad,
                        fincaGeneral1,
                        bloqueGeneral1.toInt(),
                        tipoSiembra,
                        procedimiento,
                        calibre,
                        semanaGeneral1.toInt(),
                        metros.toInt(),
                        bulbos.toInt(),
                        semanaCabe.toInt(),
                        bloqueCabe.toInt(),
                        fincaCabe,
                        tamanioCama,
                        brote,
                        otraPrueba,
                        valvulaGeneral.toInt(),
                        ladoGeneral1,
                        etiquetaGeneral1
                    )
                )
            }

        }
    }

    override fun onItemClick(position: Int, cama: Int, variedad: String, tipoSiembra: String, procedimiento: String, prueba1: String,
                             prueba2: String, fincaCabe: String, semanaCabe: Int, bloqueCabe: Int, metros: Int, calibre: String,
                             bulbos: Int, tamanioCama: String, brote: String, origen: String, otraPrueba: String, fechaGeneral1: String,
                             semanaGeneral1: Int, fincaGeneral1: String, valvulaGeneral: Int, bloqueGeneral1: Int, ladoGeneral1: String,
                             etiquetaGeneral1: String) {
        Log.i("click", "La posicion que eligió es: ${position}")
        Log.i("click", "Lo que eligio es: ${valvulaGeneral}, ${semanaGeneral1}, ${semanaCabe}")

        val intent = Intent(
            this,
            EditarDatosSiembra::class.java
        )
        intent.putExtra("posicion",position)
        intent.putExtra("Fecha", fechaGeneral1 )
        intent.putExtra("Semana", semanaGeneral1)
        intent.putExtra("Bloque", bloqueGeneral1)
        intent.putExtra("Valvula", valvulaGeneral)
        intent.putExtra("Finca", fincaGeneral1)
        intent.putExtra("Lado", ladoGeneral1)
        intent.putExtra("Etiqueta", etiquetaGeneral1)
        intent.putExtra("Cama", cama)
        intent.putExtra("Variedad", variedad)
        intent.putExtra("TipoSiembra",tipoSiembra)
        intent.putExtra("Procedimiento", procedimiento)
        intent.putExtra("Prueba1", prueba1)
        intent.putExtra("Prueba2", prueba2)
        intent.putExtra("FincaCabe", fincaCabe)
        intent.putExtra("SemanaCabe", semanaCabe)
        intent.putExtra("BloqueCabe", bloqueCabe)
        intent.putExtra("Metros", metros)
        intent.putExtra("Calibre", calibre)
        intent.putExtra("Bulbos", bulbos)
        intent.putExtra("TamanioCama", tamanioCama)
        intent.putExtra("Brote", brote)
        intent.putExtra("Origen", origen)
        intent.putExtra("OtraPrueba", otraPrueba)
        startActivity(intent)
    }


}