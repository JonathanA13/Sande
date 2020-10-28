package com.example.sande_siembra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.sande_siembra.modelo.Cabecera1
import com.example.sande_siembra.modelo.DatosSiembra
import kotlinx.android.synthetic.main.activity_editar_datos_siembra.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter

class EditarDatosSiembra : AppCompatActivity(), LifecycleObserver {

    var calibreAntiguo = ""
    var metrosAntiguos = 0
    var etiqueta = ""
    var finca = ""
    var listaDatosSiembra = arrayListOf<DatosSiembra>()
    var elementoSeleccionado = 0
    var tamanioCamaRecepcion = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_datos_siembra)

        cargarEnLista()

        tv_FechaGeneral.text = intent.getStringExtra("Fecha")
        tv_SemanaGeneral.text = intent.getIntExtra("Semana", 0).toString()
        var fincaRecepcion = ""
        finca = intent.getStringExtra("Finca").toString()
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
        metrosAntiguos = intent.getIntExtra("Metros", 0)
        tamanioCamaRecepcion = intent.getStringExtra("TamanioCama").toString()
        val siembraRecepcion = intent.getStringExtra("TipoSiembra")
        val variedadRecepcion = intent.getStringExtra("Variedad")
        val procedimientoRecepcion = intent.getStringExtra("Procedimiento")
        val broteRecepcion = intent.getStringExtra("Brote")
        val origenRecepcion = intent.getStringExtra("Origen")
        val prueba1Recepcion = intent.getStringExtra("Prueba1")
        val prueba2Recepcion = intent.getStringExtra("Prueba2")
        val fincaCabeRecepcion = intent.getStringExtra("FincaCabe")
        val calibreRecepcion = intent.getStringExtra("Calibre")
        etiqueta = intent.getStringExtra("Etiqueta").toString()
        tv_BulbosCalcular.text = intent.getIntExtra("Bulbos",0).toString()
        calibreAntiguo = intent.getStringExtra("Calibre").toString()
        elementoSeleccionado = intent.getIntExtra("posicion",0)

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

        btnEditar.setOnClickListener{ camposEditar(elementoSeleccionado) }
        btnCalcularBulbos.isEnabled = false


    }

    override fun onResume() {
        super.onResume()

        var metrosEditado: Int = 0
        var calibreEditado: String = ""
        var bandera = true

        et_Metros.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btnCalcularBulbos.isEnabled = true
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i("Estado", "El valor editado antes es: ${et_Metros.hint}")
            }
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                if (!s.toString().trim().isEmpty())
                {
                    btnCalcularBulbos.isEnabled= true
                    Log.i("Estado", "Entra aquí 1")
                    Log.i("Estado", "El valor editado es: ${et_Metros.text}")
                    tv_BulbosCalcular.text = ""
                    metrosEditado = et_Metros.text.toString().toInt()

                }
                else
                {
                    btnCalcularBulbos.isEnabled=false
                    Log.i("Estado", "Entra aquí 2")
                    Log.i("Estado", "El valor oculto es: ${et_Metros.hint}")
                    metrosEditado = et_Metros.hint.toString().toInt()
                }
            }
        })


        spinnerCalibre.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                btnCalcularBulbos.isEnabled= false
                Log.i("Estado","Entra aquí 3")
                Log.i("Estado","El valor seleccionado es: ${spinnerCalibre.selectedItem}")
                calibreEditado = spinnerCalibre.selectedItem.toString()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (spinnerCalibre.selectedItem.equals(calibreAntiguo) && et_Metros.hint.toString().toInt() == metrosAntiguos && bandera == true){
                    btnCalcularBulbos.isEnabled= false
                    Log.i("Estado","Entra aquí 4")
                    Log.i("Estado","El valor seleccionado es: ${spinnerCalibre.selectedItem}")
                    calibreEditado = spinnerCalibre.selectedItem.toString()
                }  else if(spinnerCalibre.selectedItem.equals(calibreAntiguo) && et_Metros.hint.toString().toInt() == metrosAntiguos && bandera == false){
                    btnCalcularBulbos.isEnabled= true
                    tv_BulbosCalcular.text = ""
                    Log.i("Estado","Entra aquí 5")
                    Log.i("Estado","El valor seleccionado es: ${spinnerCalibre.selectedItem}")
                    if (et_Metros.length() == 0){
                        metrosEditado = et_Metros.hint.toString().toInt()
                        Log.i("Estado","El valor seleccionado es: ${metrosEditado}")
                    } else {
                        metrosEditado = et_Metros.text.toString().toInt()
                        Log.i("Estado","El valor seleccionado es: ${metrosEditado}")
                    }
                    //metrosEditado = et_Metros.text.toString().toInt()
                    calibreEditado = spinnerCalibre.selectedItem.toString()

                } else if(spinnerCalibre.selectedItem.equals(calibreAntiguo) && et_Metros.text.toString().toInt() != metrosAntiguos && bandera == false){
                    btnCalcularBulbos.isEnabled= true
                    Log.i("Estado","Entra aquí 6")
                    Log.i("Estado","El valor seleccionado es: ${spinnerCalibre.selectedItem}")

                    metrosEditado = et_Metros.text.toString().toInt()
                    calibreEditado = spinnerCalibre.selectedItem.toString()

                } else if (spinnerCalibre.selectedItem.equals(calibreAntiguo) && et_Metros.length() == 0 ){
                    btnCalcularBulbos.isEnabled= true
                    metrosEditado = et_Metros.hint.toString().toInt()
                    calibreEditado = spinnerCalibre.selectedItem.toString()
                    Log.i("Estado","Entra aquí 7")
                    Log.i("Estado","El valor seleccionado es: ${spinnerCalibre.selectedItem}")
                    bandera = false
                } else if (spinnerCalibre.selectedItem.equals(calibreAntiguo) && et_Metros.length() > 0){
                    btnCalcularBulbos.isEnabled= true
                    metrosEditado = et_Metros.hint.toString().toInt()
                    calibreEditado = spinnerCalibre.selectedItem.toString()
                    Log.i("Estado","Entra aquí 8")
                    Log.i("Estado","El valor seleccionado es: ${spinnerCalibre.selectedItem}")
                    bandera = false
                } else if(!spinnerCalibre.selectedItem.equals(calibreAntiguo)) {
                    btnCalcularBulbos.isEnabled= true
                    tv_BulbosCalcular.text = ""
                    if (et_Metros.length() == 0){
                        metrosEditado = et_Metros.hint.toString().toInt()
                    } else {
                        metrosEditado = et_Metros.text.toString().toInt()
                    }
                    Log.i("Estado","Entra aquí 9")
                    Log.i("Estado","El valor seleccionado es: ${spinnerCalibre.selectedItem}")
                    //tv_BulbosCalcular.text = "Vacío"
                    calibreEditado = spinnerCalibre.selectedItem.toString()
                    bandera = false
                }

            }

        })

        spinnerTamanioCama.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                if (!spinnerTamanioCama.selectedItem.equals(tamanioCamaRecepcion)){
                    tv_BulbosCalcular.text = ""
                    btnCalcularBulbos.isEnabled = true
                }

                if (et_Metros.length() == 0){
                    metrosEditado = et_Metros.hint.toString().toInt()
                    Log.i("Estado","El valor seleccionado es: ${metrosEditado}")
                } else {
                    metrosEditado = et_Metros.text.toString().toInt()
                    Log.i("Estado","El valor seleccionado es: ${metrosEditado}")
                }

            }

        } )

        btnCalcularBulbos.setOnClickListener{
            Log.i("datillos", "Los datos son: ${metrosEditado}")
            Log.i("datillos", "Los datos son: ${calibreEditado}")
            if(etiqueta.equals("Bulbos")){
                calcularBulbosEdicion(metrosEditado, calibreEditado)
            } else if (etiqueta.equals("Flores") && finca.equals("S2")) {
                calcularFloresS2Edicion(metrosEditado, calibreEditado)
            } else if (etiqueta.equals("Flores") && finca.equals("S4")) {
                calcularFloresS4Edicion(metrosEditado, calibreEditado)
            }
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

    fun camposEditar(posicion: Int){

        //var bulbosEditar: Int = 0
        if (tv_BulbosCalcular.text.isEmpty() || tv_BulbosCalcular.text.toString().toInt() == 0 ){
            if (tv_BulbosCalcular.text.length == 0)
                validaciones_Campos_Vacíos("Bulbos")
            else if (et_Metros.text.toString().toInt() == 0)
                validaciones_Campos_Vacíos("Metros")
            else if (tv_BulbosCalcular.text.toString().toInt() == 0)
                validaciones_Campos_Vacíos("Bulbos")

        }
        else {

            Log.i("posicion", "La posicion de la lista es: ${posicion}")
            val camaEditar: Int
            if( et_Cama.length() == 0){
                camaEditar = et_Cama.hint.toString().toInt()
                //Log.i("editar","La edicion es: ${camaEditar}")
            } else {
                camaEditar = et_Cama.text.toString().toInt()
                //Log.i("editar","La edicion es: ${camaEditar}")
            }

            val tamanioCamaEditar = spinnerTamanioCama.selectedItem.toString()
            val tipoSiembraEditar = spinnerTipoSiembra.selectedItem.toString()
            val variedadEditar = spinnerVariedad.selectedItem.toString()
            val procedimientoEditar = spinnerProce.selectedItem.toString()
            val broteEditar = spinnerBrote.selectedItem.toString()
            val origenEditar = spinnerOrigen.selectedItem.toString()
            //Log.i("editar","La edicion es: ${origenEditar}")
            val prueba1Editar = spinnerPruebas1.selectedItem.toString()
            val prueba2Editar = spinnerPruebas2.selectedItem.toString()

            val otraPruebaEditar: String
            if( et_OtraPrueba.length() == 0){
                otraPruebaEditar = et_OtraPrueba.hint.toString()
            } else {
                otraPruebaEditar = et_OtraPrueba.text.toString()
            }

            val semanaCabeEditar: Int
            if( et_SemanaCabe.length() == 0){
                semanaCabeEditar = et_SemanaCabe.hint.toString().toInt()
            } else {
                semanaCabeEditar = et_SemanaCabe.text.toString().toInt()
            }

            val bloqueCabe: Int
            if( et_BloqueCabe.length() == 0){
                bloqueCabe = et_BloqueCabe.hint.toString().toInt()
            } else {
                bloqueCabe = et_BloqueCabe.text.toString().toInt()
            }

            val fincaCabeEditar = spinnerFincaCabe.selectedItem.toString()

            val metrosEditar: Int
            if (et_Metros.length() == 0){
                metrosEditar = et_Metros.hint.toString().toInt()
                //Log.i("editar","La edicion es: ${metrosEditar}")
            } else {
                metrosEditar = et_Metros.text.toString().toInt()
                //Log.i("editar","La edicion es: ${metrosEditar}")
            }

            val calibreEditar = spinnerCalibre.selectedItem.toString()

            val bulbosEditar = tv_BulbosCalcular.text.toString().toInt()

            listaDatosSiembra[posicion].cama = camaEditar
            listaDatosSiembra[posicion]. tamanioCama = tamanioCamaEditar
            listaDatosSiembra[posicion]. tipoSiembra = tipoSiembraEditar
            listaDatosSiembra[posicion]. variedad = variedadEditar

            listaDatosSiembra[posicion]. procedimiento = procedimientoEditar
            listaDatosSiembra[posicion]. brote = broteEditar
            listaDatosSiembra[posicion].origen = origenEditar

            listaDatosSiembra[posicion].prueba1 = prueba1Editar
            listaDatosSiembra[posicion].prueba2 = prueba2Editar
            listaDatosSiembra[posicion].otraPrueba = otraPruebaEditar

            listaDatosSiembra[posicion].semanaCabe = semanaCabeEditar
            listaDatosSiembra[posicion].bloqueCabe = bloqueCabe
            listaDatosSiembra[posicion].fincaCabe = fincaCabeEditar

            listaDatosSiembra[posicion].metros = metrosEditar
            listaDatosSiembra[posicion].calibre = calibreEditar
            listaDatosSiembra[posicion].bulbos = bulbosEditar
            guardarArchivo()

        }

    }

    fun validaciones_Campos_Vacíos(mensaje: String){
        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogIncorrect))
        with(builder)
        {
            setTitle("MENSAJE DE ERROR")
            setMessage("INGRESE \"${mensaje}\", EL CAMPO ESTÁ VACÍO")
            builder.setPositiveButton("OK") { dialogInterface, i ->
                Log.i("Pantalla", "aceptar")
            }
            show()
        }
    }

    fun cargarEnLista(){
        val file = File("/sdcard/ExportarDatosCSV/DatosSiembra4.csv")
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
        Log.i("lista", "La lista es: ${listaDatosSiembra}")

    }

    fun guardarArchivo(){
        val file = File("/sdcard/ExportarDatosCSV/DatosSiembra4.csv")

        val datosRecibidos = Cabecera1("Fecha","Cama","Prueba 1","Prueba 2","Origen",
            "Variedad","Finca","Bloque","Tipo Siembra","Procedimiento","Calibre",
            "Semana","Metros","Bulbos","Semana Cabe","Bloque Cabe","Finca Cabe",
            "Tamanio Cama","Brote","Prueba 3","Valvula","Lado","Etiqueta")


        //val file = File("/sdcard/ExportarDatosCSV/DatosSiembra4.csv")
        // /sdcard/Download/DatosSiembra2.csv
        // /sdcard/Download/DatosSiembra1.xlsx
        val ingreso = FileOutputStream(file,false)
        ingreso.bufferedWriter().use { out ->
            out.write("${datosRecibidos}")
        }

        ingreso.flush()
        ingreso.close()

        try {
            //val fileWriter = FileWriter(file)

            for (registro in listaDatosSiembra) {
                exportarCSV(registro.cama,registro.variedad,registro.tipoSiembra,registro.procedimiento,
                registro.prueba1,registro.prueba2,registro.fincaCabe,registro.semanaCabe,registro.bloqueCabe,
                registro.metros,registro.calibre,registro.bulbos,registro.tamanioCama,registro.brote,registro.origen,
                registro.otraPrueba,registro.fechaGeneral1,registro.semanaGeneral1,registro.fincaGeneral1,registro.valvulaGeneral,
                registro.bloqueGeneral1,registro.ladoGeneral1,registro.etiquetaGeneral1)

                /*fileWriter.append(registro.fechaGeneral1)
                fileWriter.append(",")
                fileWriter.append("${registro.cama}")
                fileWriter.append(",")
                fileWriter.append("${registro.prueba1}")
                fileWriter.append(",")
                fileWriter.append("${registro.prueba2}")
                fileWriter.append(",")
                fileWriter.append("${registro.origen}")
                fileWriter.append(",")
                fileWriter.append("${registro.variedad}")
                fileWriter.append(",")
                fileWriter.append("${registro.fincaGeneral1}")
                fileWriter.append(",")
                fileWriter.append("${registro.bloqueGeneral1}")
                fileWriter.append(",")
                fileWriter.append("${registro.tipoSiembra}")
                fileWriter.append(",")
                fileWriter.append("${registro.procedimiento}")
                fileWriter.append(",")
                fileWriter.append("${registro.calibre}")
                fileWriter.append(",")
                fileWriter.append("${registro.semanaGeneral1}")
                fileWriter.append(",")
                fileWriter.append("${registro.metros}")
                fileWriter.append(",")
                fileWriter.append("${registro.bulbos}")
                fileWriter.append(",")
                fileWriter.append("${registro.semanaCabe}")
                fileWriter.append(",")
                fileWriter.append("${registro.bloqueCabe}")
                fileWriter.append(",")
                fileWriter.append("${registro.fincaCabe}")
                fileWriter.append(",")
                fileWriter.append("${registro.tamanioCama}")
                fileWriter.append(",")
                fileWriter.append("${registro.brote}")
                fileWriter.append(",")
                fileWriter.append("${registro.otraPrueba}")
                fileWriter.append(",")
                fileWriter.append("${registro.valvulaGeneral}")
                fileWriter.append(",")
                fileWriter.append("${registro.ladoGeneral1}")
                fileWriter.append(",")
                fileWriter.append("${registro.etiquetaGeneral1}")
                fileWriter.append("\n")*/

                /*fileWriter.write(registro.fechaGeneral1)
                fileWriter.write(",")
                fileWriter.write("${registro.cama}")
                fileWriter.write(",")
                fileWriter.write("${registro.prueba1}")
                fileWriter.write(",")
                fileWriter.write("${registro.prueba2}")
                fileWriter.write(",")
                fileWriter.write("${registro.origen}")
                fileWriter.write(",")
                fileWriter.write("${registro.variedad}")
                fileWriter.write(",")
                fileWriter.write("${registro.fincaGeneral1}")
                fileWriter.write(",")
                fileWriter.write("${registro.bloqueGeneral1}")
                fileWriter.write(",")
                fileWriter.write("${registro.tipoSiembra}")
                fileWriter.write(",")
                fileWriter.write("${registro.procedimiento}")
                fileWriter.write(",")
                fileWriter.write("${registro.calibre}")
                fileWriter.write(",")
                fileWriter.write("${registro.semanaGeneral1}")
                fileWriter.write(",")
                fileWriter.write("${registro.metros}")
                fileWriter.write(",")
                fileWriter.write("${registro.bulbos}")
                fileWriter.write(",")
                fileWriter.write("${registro.semanaCabe}")
                fileWriter.write(",")
                fileWriter.write("${registro.bloqueCabe}")
                fileWriter.write(",")
                fileWriter.write("${registro.fincaCabe}")
                fileWriter.write(",")
                fileWriter.write("${registro.tamanioCama}")
                fileWriter.write(",")
                fileWriter.write("${registro.brote}")
                fileWriter.write(",")
                fileWriter.write("${registro.otraPrueba}")
                fileWriter.write(",")
                fileWriter.write("${registro.valvulaGeneral}")
                fileWriter.write(",")
                fileWriter.write("${registro.ladoGeneral1}")
                fileWriter.write(",")
                fileWriter.write("${registro.etiquetaGeneral1}")
                fileWriter.write("\n")*/

            }
            /*fileWriter.close()
            Toast.makeText(
                this,
                "SE CREO EL ARCHIVO CSV EXITOSAMENTE",
                Toast.LENGTH_LONG
            ).show()*/
        } catch (e: Exception) {
        }

        val intentExplicito = Intent(
            this,
            Datos::class.java
        )
        startActivity(intentExplicito)

    }

    fun exportarCSV(cama: Int, variedad: String, tipoSiembra: String, procedimiento: String, prueba1: String,
                    prueba2: String, fincaCabe: String, semanaCabe: Int, bloqueCabe: Int, metros: Int, calibre: String,
                    bulbos: Int, tamanioCama: String, brote: String, origen: String, otraPrueba: String, fechaGeneral1: String,
                    semanaGeneral1: Int, fincaGeneral1: String, valvulaGeneral: Int, bloqueGeneral1: Int, ladoGeneral1: String,
                    etiquetaGeneral1: String) {

        val datosRecibidos = DatosSiembra(fechaGeneral1,cama,prueba1,prueba2,origen,variedad,fincaGeneral1,
            bloqueGeneral1,tipoSiembra,procedimiento,calibre,semanaGeneral1,
            metros, bulbos, semanaCabe, bloqueCabe, fincaCabe, tamanioCama, brote,
            otraPrueba, valvulaGeneral, ladoGeneral1,etiquetaGeneral1)

        try {
            val file = File("/sdcard/ExportarDatosCSV/DatosSiembra4.csv")
            //val file = File("/sdcard/Download/Libro2.csv.xlsx")
            //val file = File("/sdcard/Download/DatosSiembra2.csv")
            // /sdcard/Download/DatosSiembra2.csv
            // /sdcard/Download/DatosSiembra1.xlsx
            val ingreso = FileOutputStream(file,true)
            ingreso.bufferedWriter().use { out ->
                out.write("${datosRecibidos}")
            }

            ingreso.flush()
            ingreso.close()

            /*Toast.makeText(
                this,
                "SE CREO EL ARCHIVO CSV EXITOSAMENTE",
                Toast.LENGTH_LONG
            ).show()*/
            //val toast = Toast.makeText(this, "DATO GUARDADO CORRECTAMENTE", Toast.LENGTH_SHORT)
            //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            //toast.show()

        } catch (e: Exception){
            Log.i("error", "sucedio un error ${e}")
        }


    }


    fun calcularBulbosEdicion(metrosEditado: Int, calibreEditado: String){
        Log.i("datillos", "Estos son los datos en la funcion: ${metrosEditado}")
        Log.i("datillos", "Estos son los datos en la funcion: ${calibreEditado}")

        var resultado = 0

        if(calibreEditado.equals("0/4")){
            resultado = metrosEditado * 200
        } else if (calibreEditado.equals("4/6")){
            resultado = metrosEditado * 140
        } else if (calibreEditado.equals("6/9")){
            resultado = metrosEditado * 60
        } else if (calibreEditado.equals("9/12")){
            resultado = metrosEditado * 48
        }

        tv_BulbosCalcular.text = resultado.toString()

    }


    fun calcularFloresS2Edicion(metrosEditado: Int, calibreEditado: String){
        var resultado = 0
        val tamanioCama = spinnerTamanioCama.selectedItem.toString()
        Log.i("datillos", "El tamaño es: ${tamanioCama}")
        //val tipoVariedad = cmbVariedad.selectedItem.toString()

        if(calibreEditado.equals("9/12") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 44
        } else if (calibreEditado.equals("9/12") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 35
        } else if (calibreEditado.equals("12/15") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 28
        } else if (calibreEditado.equals("12/15") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 22
        } else if (calibreEditado.equals("15/18") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 20
        } else if (calibreEditado.equals("15/18") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 16
        } else if (calibreEditado.equals("18/20") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 16
        } else if (calibreEditado.equals("18/20") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 13
        } else if (calibreEditado.equals("20/26") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 9
        } else if (calibreEditado.equals("20/26") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 7
        } else if (calibreEditado.equals("26+") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 9
        } else if (calibreEditado.equals("26+") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 5
        }

        tv_BulbosCalcular.text = resultado.toString()

    }

    fun calcularFloresS4Edicion(metrosEditado: Int, calibreEditado: String){
        var resultado = 0
        val tamanioCama = spinnerTamanioCama.selectedItem.toString()
        Log.i("datillos", "El tamaño es: ${tamanioCama}")

        if(calibreEditado.equals("9/12") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 28
        } else if (calibreEditado.equals("9/12") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 22
        } else if (calibreEditado.equals("12/15") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 20
        } else if (calibreEditado.equals("12/15") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 16
        } else if (calibreEditado.equals("15/18") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 16
        } else if (calibreEditado.equals("15/18") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 13
        } else if (calibreEditado.equals("18/20") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 12
        } else if (calibreEditado.equals("18/20") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 10
        } else if (calibreEditado.equals("20/26") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 9
        } else if (calibreEditado.equals("20/26") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 7
        } else if (calibreEditado.equals("26+") && tamanioCama.equals("1.20 mts")){
            resultado = metrosEditado * 6
        } else if (calibreEditado.equals("26+") && tamanioCama.equals("0.9 mts")){
            resultado = metrosEditado * 5
        }

        tv_BulbosCalcular.text = resultado.toString()
    }

}

// ****************************************************** FUNCIONES COMENTADAS *******************************************

//var contadorTamanioCama = 0
/*for (valor in tamanioCama){
    if(valor.equals(tamanioCama)){
        tamanioCama.get(contadorTamanioCama)
    }
    contadorTamanioCama = contadorTamanioCama + 1
}*/
//tamanioCama.

/*if (!spinnerTamanioCama.selectedItem.toString().equals("1.20 mts")){
            tamanioCamaEditar = spinnerTamanioCama.selectedItem.toString()
            Log.i("editar","La edicion es: ${tamanioCamaEditar}")
        } else {
            tamanioCamaEditar = spinnerTamanioCama.selectedItem.toString()
            Log.i("editar","La edicion es: ${tamanioCamaEditar}")
        }*/




/*override fun onResume() {
        super.onResume()
        Log.i("Activity","OnResume")

        verificar()

        /*btnCalcularBulbos.isEnabled = false
        val metrosAntiguo = intent.getIntExtra("Metros",0).toString()
        val metrosEditar: Int
        if (et_Metros.length() > 0 ){

            metrosEditar = et_Metros.text.toString().toInt()
            if (metrosEditar.equals(metrosAntiguo)){
                Log.i("cambio", "El valor es el mismo")
            } else {
                btnCalcularBulbos.isEnabled = true
                Log.i("cambio","La edicion con editar es: ${metrosEditar}")
            }

            /*metrosEditar = et_Metros.hint.toString().toInt()
            Log.i("cambio","La edicion sin editar es: ${metrosEditar}")*/
        }*/ /*else {
            metrosEditar = et_Metros.text.toString().toInt()
            if (metrosEditar.equals(metrosAntiguo)){
                Log.i("cambio", "El valor es el mismo")
            } else {
                btnCalcularBulbos.isEnabled = true
                Log.i("cambio","La edicion con editar es: ${metrosEditar}")
            }
        }*/
        //onResume()

    }*/

/*@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
fun verificar(){
    btnCalcularBulbos.isEnabled = false
    val metrosAntiguo = intent.getIntExtra("Metros",0).toString()
    val metrosEditar: Int
    if (et_Metros.length() > 0 ){

        metrosEditar = et_Metros.text.toString().toInt()
        if (metrosEditar.equals(metrosAntiguo)){
            Log.i("cambio", "El valor es el mismo")
        } else {
            btnCalcularBulbos.isEnabled = true
            Log.i("cambio","La edicion con editar es: ${metrosEditar}")
        }

        /*metrosEditar = et_Metros.hint.toString().toInt()
        Log.i("cambio","La edicion sin editar es: ${metrosEditar}")*/
    }
}*/

/*fun verificar(){

}*/

/*fun calcularBulbosEdicion(metrosEditado: Int, calibreEditado: String){
        Log.i("datosEdit","Los metros nuevos son: ${metrosEditado}")
        Log.i("datosEdit","El calibre nuevo es: ${calibreEditado}")

    }*/