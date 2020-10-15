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
        val pruebaAdicionalRecepcion = intent.getStringExtra("OtraPrueba")
        val fincaCabeRecepcion = intent.getStringExtra("FincaCabe")
        val calibreRecepcion = intent.getStringExtra("Calibre")

        if (tamanioCamaRecepcion != null && siembraRecepcion != null && variedadRecepcion != null && procedimientoRecepcion != null && broteRecepcion != null &&
            origenRecepcion != null && prueba1Recepcion != null && prueba2Recepcion != null && pruebaAdicionalRecepcion != null && fincaCabeRecepcion != null &&
            calibreRecepcion != null
        ) {
            llenarSpinners(
                tamanioCamaRecepcion,
                siembraRecepcion,
                variedadRecepcion,
                procedimientoRecepcion,
                broteRecepcion,
                origenRecepcion,
                prueba1Recepcion,
                prueba2Recepcion,
                pruebaAdicionalRecepcion,
                fincaCabeRecepcion,
                calibreRecepcion
            )
        }

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
        RpruebaAdicionalRecepcion: String,
        RfincaCabeRecepcion: String,
        RcalibreRecepcion: String
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
