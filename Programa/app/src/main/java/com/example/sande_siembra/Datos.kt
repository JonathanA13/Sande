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
import java.util.*
import java.util.EnumSet.of
import java.util.List.of

class Datos : AppCompatActivity(), RecyclerAdapter.OnDatosSiembraClickListener {

    val cal = Calendar.getInstance()
    val day=cal[Calendar.DATE]

    var listaDatosSiembra = arrayListOf<DatosSiembra>()
    private var especie = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)
        especie = intent.getStringExtra("especie").toString()
        btn_atras.setOnClickListener{ irInicio() }
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        leerRegistrosCSV()
        Log.i("listita", "la lista es: ${listaDatosSiembra}")
        Log.i("listita", "la cantidad de registros de lista es: ${listaDatosSiembra.size}")
        recyclerView.adapter = RecyclerAdapter(this, listaDatosSiembra, this)
    }

    fun leerRegistrosCSV(){
        val file = File("/sdcard/DatosSiembraCalla/Callas${day}-11-2020.csv")
        //val file = File("/sdcard/ExportarDatosCSV/DatosSiembra4.csv")
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
            val tipoSiembra = tokens[6]
            val color = tokens[7]
            val fincaGeneral1 = tokens[8]
            val bloqueGeneral1 = tokens[9]

            val etiquetaGeneral1 = tokens[10]
            val procedimiento = tokens[11]
            val calibre = tokens[12]
            val semanaGeneral1 = tokens[13]
            val metros = tokens[14]

            val bulbos = tokens[15]
            val semanaCabe = tokens[16]
            val bloqueCabe = tokens[17]
            val fincaCabe = tokens[18]
            val tamanioCama = tokens[19]

            val brote = tokens[20]
            val otraPrueba = tokens[21]
            val valvulaGeneral = tokens[22]
            val ladoGeneral1 = tokens[23]

            val codigo = tokens[24]

            if ( index != 0) {
                listaDatosSiembra.add(
                    DatosSiembra(
                        fecha,
                        cama.toInt(),
                        prueba1,
                        prueba2,
                        origen,
                        variedad,
                        tipoSiembra,
                        color,
                        fincaGeneral1,
                        bloqueGeneral1.toInt(),
                        etiquetaGeneral1,
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
                        codigo
                    )
                )
            }

        }
    }

    override fun onItemClick(position: Int, cama: Int, variedad: String, tipoSiembra: String, procedimiento: String, prueba1: String,
                             prueba2: String, fincaCabe: String, semanaCabe: Int, bloqueCabe: Int, metros: Int, calibre: String,
                             bulbos: Int, tamanioCama: String, brote: String, origen: String, otraPrueba: String, fechaGeneral1: String,
                             semanaGeneral1: Int, fincaGeneral1: String, valvulaGeneral: Int, bloqueGeneral1: Int, ladoGeneral1: String,
                             etiquetaGeneral1: String, codigo: String) {
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
        intent.putExtra("Codigo",codigo)
        intent.putExtra("especie", especie)
        startActivity(intent)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    fun irInicio(){
        val intent = Intent(
            this,
            MenuInicio::class.java
        )
        intent.putExtra("especie",especie)
        startActivity(intent)
    }


}

// *************************************************** FUNCIONES COMENTADAS ******************************************************************

/*val listaDatosSiembra = listOf(
            DatosSiembra(
                10, "Vermer", "Vermer", "Vermer",
                "Vermer", "Vermer", "Vermer", 15, 20, 25, "Vermer",
                15, "Vermer", "Vermer", "Vermer", "Vermer", "12 Oct 2020", 15,
                "Vermer", 12, 5, "Vermer", "Vermer"
            )
        )*/