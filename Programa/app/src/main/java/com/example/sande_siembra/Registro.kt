package com.example.sande_siembra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val fecha = intent.getStringExtra("Fecha")
        val semana = intent.getIntExtra("Semana",0)
        val bloque = intent.getIntExtra("Bloque",0)
        val valvula = intent.getIntExtra("Valvula",0)

        txtFechaRegistro.text = fecha
        txtSemanaRegistro.text = semana.toString()
        txtViewBloqueRegistro.text = bloque.toString()
        txt_valvula.text = valvula.toString()

        definir()

        btnGuardar.setOnClickListener{ obtener() }
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

        val pl_proce = arrayOf("AF","AF-AF","HOLANDA","PL")
        val spinner3: Spinner = findViewById(R.id.cmbProce)
        val adapter3: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, pl_proce)
        spinner3.setAdapter(adapter3)

        val prueba1 = arrayOf("PRUEBA A",
            "PRUEBA B",
            "PRUEBA D",
            "PRUEBA E",
            "PRUEBA FERTILIZACION VALVULA 4",
            "PRUEBA FERTILIZACION VALVULA 5",
            "TINAS",
            "NINGUNA")
        val spinner4: Spinner = findViewById(R.id.cmbPruebas1)
        val adapter4: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, prueba1)
        spinner4.setAdapter(adapter4)

        val prueba2 = arrayOf("ABIERTOS CON LA MANO",
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
            "TESTIGO",
            "TESTIGO DESINFECCION",
            "VAPORIZADA",
            "NINGUNA")
        val spinner5: Spinner = findViewById(R.id.cmbPruebas2)
        val adapter5: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, prueba2)
        spinner5.setAdapter(adapter5)

        val finca = arrayOf("S2", "S4")
        val spinner6: Spinner = findViewById(R.id.cmbFincaCabe)
        val adapter6: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, finca)
        spinner6.setAdapter(adapter6)

        val calibre = arrayOf("0/4","6/9","9/12","12/15","15/18","18/20","20/+")
        val spinner7: Spinner = findViewById(R.id.cmbCalibre)
        val adapter7: ArrayAdapter<Any?> =  ArrayAdapter<Any?>(this, R.layout.size2, calibre)
        spinner7.setAdapter(adapter7)

    }

    fun obtener(){
        val cama = editTxtCama.text.toString().toInt()
        val variedad = cmbVariedad.selectedItem.toString()
        val tipoSiembra = cmbTipoSiembra.selectedItem.toString()
        val procedimiento = cmbProce.selectedItem.toString()
        val prueba1 = cmbPruebas1.selectedItem.toString()
        val prueba2 = cmbPruebas2.selectedItem.toString()
        val finca = cmbFincaCabe.selectedItem.toString()
        val semana = editTxtSemanaCabe.text.toString().toInt()
        val bloque = editTxtBloqueCabe.text.toString().toInt()
        val metros = editTxtMetros.text.toString().toInt()
        val calibre = cmbCalibre.selectedItem.toString()
        val bulbos = editTextNumber7.text.toString().toInt()

        db.collection("SiembraDatos").add(
            hashMapOf("Cama" to calibre,
            "Variedad" to variedad,
            "tipoSiembra" to tipoSiembra,
            "Procedimiento" to procedimiento,
            "Prueba1" to prueba1,
            "Prueba2" to prueba2,
            "Finca" to  finca,
            "Semana" to semana,
            "Bloque" to  bloque,
            "Metros" to metros,
            "Calibre" to calibre,
            "Bulbos" to bulbos)
        )

        /*
        val lado = cmbLado.selectedItem.toString()
         */
    }
}