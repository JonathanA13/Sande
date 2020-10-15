package com.example.sande_siembra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        val file = File("/sdcard/ExportarDatosCSV/DatosSiembra.csv")
        val lines: List<String> = file.readLines()
        Log.i("Fechita", "El tamaño es: ${lines.size}")
        lines.forEachIndexed { index, s ->
            val line = lines[index]
            Log.i("Fechita", "La fecha es: ${line}")
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

            Log.i("Fechita", "La fecha es: ${fecha}")
            Log.i("Fechita", "La semana es: ${prueba1}")
            Log.i("Fechita", "La semana es: ${origen}")
            Log.i("Fechita", "La semana es: ${variedad}")
            Log.i("Fechita", "La semana es: ${tipoSiembra}")
            Log.i("Fechita", "La semana es: ${valvulaGeneral}")
            Log.i("Fechita", "La semana es: ${bloqueGeneral1}")

            listaDatosSiembra.add(
                DatosSiembra(
                    cama.toInt(),
                    variedad,
                    tipoSiembra,
                    procedimiento,
                    prueba1,
                    prueba2,
                    fincaCabe,
                    semanaCabe.toInt(),
                    bloqueCabe.toInt(),
                    metros.toInt(),
                    calibre,
                    bulbos.toInt(),
                    tamanioCama,
                    brote,
                    origen,
                    otraPrueba,
                    fecha,
                    semanaGeneral1.toInt(),
                    fincaGeneral1,
                    valvulaGeneral.toInt(),
                    bloqueGeneral1.toInt(),
                    ladoGeneral1,
                    etiquetaGeneral1
                )
            )
        }

        recyclerView.adapter = RecyclerAdapter(this, listaDatosSiembra, this)
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