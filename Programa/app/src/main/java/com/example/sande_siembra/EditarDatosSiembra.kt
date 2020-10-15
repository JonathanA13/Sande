package com.example.sande_siembra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_editar_datos_siembra.*

class EditarDatosSiembra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_datos_siembra)

        tv_FechaGeneral.text = intent.getStringExtra("Fecha")
        tv_SemanaGeneral.text = intent.getIntExtra("Semana", 0).toString()
        var fincaRecepcion = ""
        val finca = intent.getStringExtra("Finca")
        if (finca.equals("S2")) {
            fincaRecepcion = "Sande 2"
        } else {
            fincaRecepcion = "Sande 4"
        }
        tv_FincaGeneral.text = fincaRecepcion
        tv_BloqueGeneral.text = intent.getIntExtra("Bloque", 0).toString()
        tv_ValvulaGeneral.text = intent.getIntExtra("Valvula", 0).toString()

        et_Cama.hint = intent.getIntExtra("Cama", 0).toString()
        //spinnerProce.
        et_OtraPrueba.hint = intent.getStringExtra("OtraPrueba")
        et_SemanaCabe.hint = intent.getIntExtra("SemanaCabe", 0).toString()
        et_BloqueCabe.hint = intent.getIntExtra("BloqueCabe", 0).toString()
        et_Metros.hint = intent.getIntExtra("Metros", 0).toString()
        val tamanioCamaRecepcion = intent.getStringExtra("TamanioCama")
        val siembraRecepcion = intent.getStringExtra("TipoSiembra")
        val variedadRecepcion = intent.getStringExtra("Variedad")
        val procedimientoRecepcion = intent.getStringExtra("Procedimiento")
        val broteRecepcion = intent.getStringExtra("Brote")
        val origenRecepcion = intent.getStringExtra("Origen")
        val prueba1Recepcion = intent.getStringExtra("Prueba1")
        val prueba2Recepcion = intent.getStringExtra("Prueba2")
        val fincaCabeRecepcion = intent.getStringExtra("FincaCabe")
        val calibreRecepcion = intent.getStringExtra("Calibre")
        val etiqueta = intent.getStringExtra("Etiqueta")

        if (tamanioCamaRecepcion != null && siembraRecepcion != null && variedadRecepcion != null && procedimientoRecepcion != null && broteRecepcion != null &&
            origenRecepcion != null && prueba1Recepcion != null && prueba2Recepcion != null && fincaCabeRecepcion != null &&
            calibreRecepcion != null && etiqueta != null) {
            llenarSpinners(
                tamanioCamaRecepcion,
                siembraRecepcion,
                variedadRecepcion,
                procedimientoRecepcion,
                broteRecepcion,
                origenRecepcion,
                prueba1Recepcion,
                prueba2Recepcion,
                fincaCabeRecepcion,
                calibreRecepcion,
                etiqueta
            )
        }

        btnEditar.setOnClickListener{ camposEditar() }

    }

    fun llenarSpinners(
        RTamanioCama: String,
        RsiembraRecepcion: String,
        RvariedadRecepcion: String,
        RprocedimientoRecepcion: String,
        RbroteRecepcion: String,
        RorigenRecepcion: String,
        Rprueba1Recepcion: String,
        Rprueba2Recepcion: String,
        RfincaCabeRecepcion: String,
        RcalibreRecepcion: String,
        etiqueta: String
    ) {

        val tamanioCama = arrayOf("0.9 mts", "1.20 mts")
        val spinnerTamanioCama: Spinner = findViewById(R.id.spinnerTamanioCama)
        val adaptador1: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.size2, tamanioCama)
        spinnerTamanioCama.setAdapter(adaptador1)
        val spinnerPosicion = adaptador1.getPosition(RTamanioCama)
        spinnerTamanioCama.setSelection(spinnerPosicion)


        val tipoSiembra = arrayOf("M.P1", "M.P2", "M.V1", "M.Z1", "M1", "M1.P1", "M1.P2", "M1.T1", "M1.T2", "M1.V1",
            "M1.Z1", "M1.Z2", "M2", "P1", "P1.T2", "P1.T3", "P1.Z2", "P2", "P3", "T1", "T2", "T3", "V0", "V0.T2", "V0.T3",
            "V1", "V1.T2", "V2", "V3", "Z", "Z1", "Z2", "Z3")
        val spinnerTipoSiembra: Spinner = findViewById(R.id.spinnerTipoSiembra)
        val adaptador2 = ArrayAdapter<Any?>(this, R.layout.size2, tipoSiembra)
        spinnerTipoSiembra.setAdapter(adaptador2)
        val spinnerPosicion2 = adaptador2.getPosition(RsiembraRecepcion)
        spinnerTipoSiembra.setSelection(spinnerPosicion2)


        val variedad = arrayOf("ACCENT", "ARANAL", "ASPEN", "AUCKLAND", "AVIGNON", "BLACK DIAMOND", "BLACK VELVET", "BUICK", "CAPTAIN ROMANCE", "CAVALESE",
            "CONFETTI", "CORDOBA", "COUPLET", "DENVER", "DIAMANTE", "E-026", "E-1129", "E-1153", "Z-1180", "E-166", "E-175", "E-233", "E-270", "E-336", "E-343", "E-364",
            "E-367", "E-369", "E-370", "E-371", "E-383", "E-434", "E-439", "E-494", "E-507", "E-512", "E-603", "E-605", "E-607", "E-613", "E-615",
            "E-616", "E-618", "E-624", "E-625", "E-626", "E-630", "E-632", "E-635", "E-636", "E-637", "E-703", "E-740", "EL CAPO", "ESCAPE", "FANTASIA",
            "GRAN PARADISO", "LA PAZ", "MANILA", "MEMORIES", "MOZART", "OLINA", "PAVIA", "PINK PANTHER", "RASPBERRY", "SAN REMO",
            "SCHWARZWALDER", "SHARP", "STRAUSS", "SUMATRA", "SUNRAY", "SUNSET", "SUNSHADE", "VERMEER", "Z-004", "Z-103", "Z-3117", "Z-371",
            "Z-199", "Z-957", "E-188", "E-353", "Z-947", "E-506", "E-632", "CAPT.3217", "CAPT.MORGAN", "CAPT.3421", "CAPT.ALMA"
        )
        val spinnerVariedad: Spinner = findViewById(R.id.spinnerVariedad)
        val adaptador3 = ArrayAdapter<Any?>(this, R.layout.size2, variedad)
        spinnerVariedad.setAdapter(adaptador3)
        val spinnerPosicion3 = adaptador3.getPosition(RvariedadRecepcion)
        spinnerVariedad.setSelection(spinnerPosicion3)


        val pl_proce = arrayOf("AF","AF-AF","af-af","HOLANDA","PL")
        val spinnerProcedimiento: Spinner = findViewById(R.id.spinnerProce)
        val adaptador4 = ArrayAdapter<Any?>(this, R.layout.size2, pl_proce)
        spinnerProcedimiento.setAdapter(adaptador4)
        val spinnerPosicion4 = adaptador4.getPosition(RprocedimientoRecepcion)
        spinnerProcedimiento.setSelection(spinnerPosicion4)


        val Brote = arrayOf("Entero con brote","Brote grande","Brote pequeño")
        val spinnerBrote: Spinner = findViewById(R.id.spinnerBrote)
        val adaptador5 = ArrayAdapter<Any?>(this, R.layout.size2, Brote)
        spinnerBrote.setAdapter(adaptador5)
        val spinnerPosicion5 = adaptador5.getPosition(RbroteRecepcion)
        spinnerBrote.setSelection(spinnerPosicion5)


        val Origen = arrayOf("NACIONAL","HOL-19","HOL-20", "HOLANDA")
        val spinnerOrigen: Spinner = findViewById(R.id.spinnerOrigen)
        val adaptador6 = ArrayAdapter<Any?>(this, R.layout.size2, Origen)
        spinnerOrigen.setAdapter(adaptador6)
        val spinnerPosicion6 = adaptador6.getPosition(RorigenRecepcion)
        spinnerOrigen.setSelection(spinnerPosicion6)


        val prueba1 = arrayOf("NINGUNA", "PRUEBA A", "PRUEBA B", "PRUEBA C", "PRUEBA D",
            "PRUEBA E", "PRUEBA FERTILIZACION VALVULA 4", "PRUEBA FERTILIZACION VALVULA 5", "TINAS")
        val spinnerPrueba1: Spinner = findViewById(R.id.spinnerPruebas1)
        val adaptador7 = ArrayAdapter<Any?>(this, R.layout.size2, prueba1)
        spinnerPrueba1.setAdapter(adaptador7)
        val spinnerPosicion7 = adaptador7.getPosition(Rprueba1Recepcion)
        spinnerPrueba1.setSelection(spinnerPosicion7)


        val prueba2 = arrayOf("NINGUNA", "ABIERTOS CON LA MANO", "BACTERIAS PLUS ORGANIC", "CRUZAMIENTOS",
            "MARCADOR 56", "MARCADOR 68", "PRUEBA DESINFECCION", "PRUEBA DOBLE AG3", "PRUEBA ONTSMET",
            "PRUEBA PRESIEMBRA ACTUAL", "PRUEBA PRESIEMBRA ACTUAL VAPORIZADA", "PRUEBA PRESIEMBRA ANTERIOR",
            "PRUEBA PRESIEMBRA ANTERIOR SIN VAPORIZAR", "SIN REVISAR ASIENTOS", "SIN VAPORIZAR", "TESTIGO",
            "TESTIGO DESINFECCION", "VAPORIZADA")
        val spinnerPrueba2: Spinner = findViewById(R.id.spinnerPruebas2)
        val adaptador8 = ArrayAdapter<Any?>(this, R.layout.size2, prueba2)
        spinnerPrueba2.setAdapter(adaptador8)
        val spinnerPosicion8 = adaptador8.getPosition(Rprueba2Recepcion)
        spinnerPrueba2.setSelection(spinnerPosicion8)


        val finca = arrayOf("S2", "S4")
        val spinnerFincaCabe: Spinner = findViewById(R.id.spinnerFincaCabe)
        val adapter9 = ArrayAdapter<Any?>(this, R.layout.size2, finca)
        spinnerFincaCabe.setAdapter(adapter9)
        val spinnerPosicion9 = adapter9.getPosition(RfincaCabeRecepcion)
        spinnerFincaCabe.setSelection(spinnerPosicion9)


        if(etiqueta.equals("Flores")){
            val calibre = arrayOf("9/12","12/15","15/18","18/20","20/26","26+")
            val spinnerCalibre: Spinner = findViewById(R.id.spinnerCalibre)
            val adaptador10 = ArrayAdapter<Any?>(this, R.layout.size2, calibre)
            spinnerCalibre.setAdapter(adaptador10)
            val spinnerPosicion10 = adaptador10.getPosition(RcalibreRecepcion)
            spinnerCalibre.setSelection(spinnerPosicion10)
        } else {
            val calibreBulbos = arrayOf("0/4","4/6","6/9","9/12")
            val spinnerCalibre: Spinner = findViewById(R.id.spinnerCalibre)
            val adaptador10 = ArrayAdapter<Any?>(this, R.layout.size2, calibreBulbos)
            spinnerCalibre.setAdapter(adaptador10)
            val spinnerPosicion10 = adaptador10.getPosition(RcalibreRecepcion)
            spinnerCalibre.setSelection(spinnerPosicion10)
        }

    }

    fun camposEditar(){

    }

}

//var contadorTamanioCama = 0
/*for (valor in tamanioCama){
    if(valor.equals(tamanioCama)){
        tamanioCama.get(contadorTamanioCama)
    }
    contadorTamanioCama = contadorTamanioCama + 1
}*/
//tamanioCama.
