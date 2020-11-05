package com.example.sande_siembra

import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sande_siembra.modelo.SincronizacionDatosSiembra
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main_menu_inicio.*
import java.io.File
import java.lang.Exception


class MenuInicio : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
        .build()

    var context = this
    var connectivity : ConnectivityManager? = null
    var info : NetworkInfo? = null

    var especie=""
    var contador = 0
    var listaSincroDatosSiembra = arrayListOf<SincronizacionDatosSiembra>()
    var guardadoRegistro = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_inicio)

        db.firestoreSettings = settings

        especie = intent.getStringExtra("especie").toString()
        Log.i("especie", "El nombre en menu inicio es: ${especie}")
        guardadoRegistro = 0



        //val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

        //File getDir ("Datos.xlsx", true)
        //File getFilesDir ()
        //val path = Environment.getStorageDirectory(Environment.DIRECTORY_DOCUMENTS)
        //val file = File(this.filesDir)

        /*val fout = OutputStreamWriter(
            openFileOutput("meminterna.xlsx", Context.MODE_PRIVATE)
        )

        fout.write("Contenido del fichero de memoria interna.")
        //fout.close()*/


        btnNuevo.setOnClickListener { botonNuevo() }
        //btn_sincro.setOnClickListener{ createXlsx(fout, "guardar") }
        //button2.setOnClickListener{ mostrarDialogoBasico() }
        btnMostrar.setOnClickListener{
            val intent = Intent(
                this,
                Datos::class.java
            )
            startActivity(intent)
        }

        /*if (!isConnected()){
            btn_sincro.isEnabled = false
            Log.i("error", "No se tiene conexion a internet")
        }*/

        btn_sincro.setOnClickListener{
            //detectar()
            val destinatario = "sandetablet@gmail.com,mestrella@sandeflowers.com"
            val asunto = "Registros de Siembra"
            val mensaje = "Adjunto los datos de siembra"

            sendEmail(destinatario,asunto,mensaje)
        }

        /*val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")*/

        /*var userId = 5
        FirebaseDatabase.getInstance().reference.child("usuario").child("1").setValue(userId)*/

        /*val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")*/

        verificar_id()


    }

    fun sendEmail(destinatario: String, asunto: String, mensaje: String){
        val miIntent = Intent(Intent.ACTION_SEND)
        miIntent.data = Uri.parse("mailto:")
        miIntent.type = "text/plain"

        miIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(destinatario))
        miIntent.putExtra(Intent.EXTRA_SUBJECT, asunto)
        miIntent.putExtra(Intent.EXTRA_TEXT, mensaje)

        try {
            startActivity(Intent.createChooser(miIntent,"Escoger un destinatario... "))
        } catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun detectar(){
        if (isConnected()){
            sincronizar()
        } else {
            val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogIncorrect))
            with(builder)
            {
                setTitle("ATENCION")
                setMessage("SE PRODUJO UN ERROR DEBIDO A QUE NO TIENE CONEXIÓN A INTERNET")
                builder.setPositiveButton("OK") { dialogInterface, i ->
                    Log.i("error", "No se tiene conexion a internet")
                }
                show()
            }
            //btn_sincro.isEnabled = false
            //Log.i("error", "No se tiene conexion a internet")
        }
    }

    fun guardar(){
        var userId = 25
        FirebaseDatabase.getInstance().reference.child("usuario").child("4").setValue(userId)
        /*val archivo = File("archivos//datos.txt")
        val ingreso = FileOutputStream(archivo, true)
        ingreso.bufferedWriter().use{out ->
            out.write("Esto es una prueba")
        }*/
    }

    fun irLista(){
        val intent = Intent(
            this,
            //Datos::class.java
            SQL_activity_sql_recycler::class.java

        )
        startActivity(intent)
    }



    private fun mostrarDialogoBasico() {
        val comprobacion = "Entra en el sí"
        val builder =
            AlertDialog.Builder(

                this
            )
        builder.setTitle("Registro de datos")
        builder.setMessage("¿Los datos se guardaron correctamente. ¿Desea Continuar ingresando datos?")
            .setPositiveButton("Sí") { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    "Guardando datos...",
                    Toast.LENGTH_SHORT
                ).show()
                Log.i("Pantalla", comprobacion)
            }
            .setNegativeButton(
                android.R.string.cancel
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Cancelando...", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                Log.i("Pantalla", comprobacion)
            }
            .setCancelable(false)
            .show()
    }

    fun botonNuevo(){

        val intentExplicito = Intent(
            this,
            MainActivity::class.java
        )
        intentExplicito.putExtra("especie", especie)
        startActivity(intentExplicito)
    }

    fun prueba(){}
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

    fun sincronizar(){

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

            listaSincroDatosSiembra.add(
                SincronizacionDatosSiembra(cama.toInt(), variedad,tipoSiembra,procedimiento,prueba1,prueba2,fincaCabe,semanaCabe.toInt(),bloqueCabe.toInt(),metros.toInt(),
                    calibre, bulbos.toInt(),tamanioCama,brote,origen,otraPrueba,fecha,semanaGeneral1.toInt(),fincaGeneral1, valvulaGeneral.toInt(),
                    bloqueGeneral1.toInt(),ladoGeneral1,etiquetaGeneral1)
            )

            /*verificar_id()
            //var numeroID = contador

            guardarBaseDatos(cama.toInt(), variedad,tipoSiembra,procedimiento,prueba1,prueba2,fincaCabe,semanaCabe.toInt(),bloqueCabe.toInt(),metros.toInt(),
                calibre, bulbos.toInt(),tamanioCama,brote,origen,otraPrueba,fecha,semanaGeneral1.toInt(),fincaGeneral1, valvulaGeneral.toInt(),
                bloqueGeneral1.toInt(),ladoGeneral1,etiquetaGeneral1)*/

        }

        guardarBaseDatos()
        /*val indices = listaSincroDatosSiembra.size
        Log.i("Cantidad", "La cantidad total es: ${indices}")
        listaSincroDatosSiembra.forEach {
            val cama = it.cama
            Log.i("Cantidad", "La cama es: ${cama}")
            val prueba = it.prueba1
            Log.i("Cantidad", "La prueba es: ${prueba}")
            val fecha = it.fechaGeneral1
            Log.i("Cantidad", "La fecha es: ${fecha}")
        }*/
        /*for (datos in listaSincroDatosSiembra){
            val fecha = datos.fechaGeneral1
            Log.i("Cantidad", "La Fecha es: ${fecha}")
        }*/


    }

    fun guardarBaseDatos(){

        val indices = listaSincroDatosSiembra.size

        var cama: Int
        var variedad: String
        var tipoSiembra: String
        var procedimiento: String
        var prueba1: String
        var prueba2: String
        var fincaCabe: String
        var semanaCabe: Int
        var bloqueCabe: Int
        var metros: Int
        var calibre: String
        var bulbos: Int
        var tamanioCama: String
        var brote: String
        var origen: String
        var otraPrueba: String
        var fechaGeneral1: String
        var semanaGeneral1: Int
        var fincaGeneral1: String
        var valvulaGeneral: Int
        var bloqueGeneral1: Int
        var ladoGeneral1: String
        var etiquetaGeneral1: String

        //verificar_id()
        var contadorsito = contador
        //var numeroID = 0
        //verificar_id()
        Log.i("Cantidad", "La cantidad total es: ${indices}")
        listaSincroDatosSiembra.forEach {
            Log.i("Contadorsito", "El contadorsito del for each es: ${contadorsito}")
            cama = it.cama
            variedad = it.variedad
            tipoSiembra = it.tipoSiembra
            procedimiento = it.procedimiento
            prueba1 = it.prueba1
            prueba2 = it.prueba2
            fincaCabe = it.fincaCabe
            semanaCabe = it.semanaCabe
            bloqueCabe = it.bloqueCabe
            metros = it.metros
            calibre = it.calibre
            bulbos = it.bulbos
            tamanioCama = it.tamanioCama
            brote = it.brote
            origen = it.origen
            otraPrueba = it.otraPrueba
            fechaGeneral1 = it.fechaGeneral1
            semanaGeneral1 = it.semanaGeneral1
            fincaGeneral1 = it.fincaGeneral1
            valvulaGeneral = it.valvulaGeneral
            bloqueGeneral1 = it.bloqueGeneral1
            ladoGeneral1 = it.ladoGeneral1
            etiquetaGeneral1 = it.etiquetaGeneral1
            //Log.i("Cantidad", "La cama es: ${cama}")
            //prueba = it.prueba1
            //Log.i("Cantidad", "La prueba es: ${prueba}")
            //fecha = it.fechaGeneral1
            //Log.i("Cantidad", "La fecha es: ${fecha}")


            Log.i("rece", "El id que se recibe es: ${contadorsito}")

            db.collection("Prueba").document("${contadorsito}").set(
                hashMapOf("Fecha" to fechaGeneral1,
                    "Semana" to semanaGeneral1, "Finca" to fincaGeneral1, "Valvula" to valvulaGeneral,
                    "Bloque" to bloqueGeneral1,
                    "Lado" to ladoGeneral1,
                    "Etiqueta" to etiquetaGeneral1,
                    "Cama" to cama,
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
                    "Bulbos" to bulbos,
                    "TamanioCama" to tamanioCama,
                    "Brote" to brote,
                    "Origen" to origen,
                    "Prueba3" to otraPrueba
                )
            )
            contadorsito = contadorsito + 1
            //verificar_id()
            Log.i("Contadorsito", "El contadorsito es: ${contadorsito}")
        }
        guardadoRegistro = guardadoRegistro + 1
        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCorrect))
        with(builder)
        {
            setTitle("CORRECTO")
            setMessage("El dato se guardó exitosamente")
            builder.setPositiveButton("OK") { dialogInterface, i ->
                if(guardadoRegistro >= 1){
                    btn_sincro.isEnabled = false
                }
            }
            show()
            if(guardadoRegistro >= 1){
                btn_sincro.isEnabled = false
            }
        }








        //verificar_id()
        //var numeroID = numeroRegistro + 1
        /*
        var numeroID = contador

        Log.i("rece", "El id que se recibe es: ${numeroID}")

        db.collection("Prueba").document("${numeroID}").set(
            hashMapOf("Fecha" to fecha,
                "Semana" to semanaGeneral1, "Finca" to fincaGeneral1, "Valvula" to valvulaGeneral,
                "Bloque" to bloqueGeneral1,
                "Lado" to ladoGeneral1,
                "Etiqueta" to etiquetaGeneral1,
                "Cama" to cama,
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
                "Bulbos" to bulbos,
                "TamanioCama" to tamanioCama,
                "Brote" to brote,
                "Origen" to origen,
                "Prueba3" to otraPrueba
            )
        )*/
    }

    fun verificar_id() {
        var contadorSecundario1 = 0
        var numeros = arrayListOf<Int>()
        db.collection("Prueba").get().addOnSuccessListener { resultado ->
            for (documento in resultado){
                numeros.add(documento.id.toInt())
                numeros.sort()
                Log.i("recibir", "La lista es: ${numeros}")
                val ultimo = numeros.last()
                Log.i("recibir", "Este es el ultimo número: ${ultimo}")
                //val idBase = documento.id.toInt()
                //Log.i("recibir","El ******************* id de la base es: ${idBase}")
                contadorSecundario1 = ultimo + 1
                //Log.i("recibir", "El ID es: ${contadorSecundario1}")
            }
            contador = contadorSecundario1
            Log.i("recibir", "El ID que se va a guardar es: ${contador}")
        }


        /*val fecha = "Sincronizacion"
        val bloque = "Sincronizacion"
        val lado = "Sincronizacion"
        val etiqueta = "Sincronizacion"
        val cama = "Sincronizacion"
        val procedimiento = "Sincronizacion"
        val variedad = "Sincronizacion"

        db.collection("SiembraDatosPrueba").add(
            hashMapOf("Fecha" to fecha,
                "Bloque" to bloque,
                "Lado" to lado,
                "Etiqueta" to etiqueta
            )
        )

        db.collection("SiembraDatosPrueba").add(
            hashMapOf("Cama" to cama,
                "Variedad" to variedad,
                "Procedimiento" to procedimiento
                )
        )*/
    }

    /*fun guardar() {
        val wb: Workbook = HSSFWorkbook()
        var cell: Cell<*, *, *>? = null
        val cellStyle: CellStyle = wb.createCellStyle()
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index)
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND)
        var sheet: Sheet? = null
        sheet = wb.createSheet("Lista de usuarios")
        var row: Row? = null
        row = sheet.createRow(0)
        cell = row.createCell(0)
        cell.setCellValue("USUARIO")
        cell.setCellStyle(cellStyle)
        sheet.createRow(1)
        cell = row.createCell(1)
        cell.setCellValue("NOMBRE")
        cell.setCellStyle(cellStyle)
        row = sheet.createRow(1)
        cell = row.createCell(0)
        cell.setCellValue("xcheko51x")
        cell = row.createCell(1)
        cell.setCellValue("Sergio Peralta")
        val file = File(getExternalFilesDir(null), "Relacion_Usuarios.xls")
        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(file)
            wb.write(outputStream)
            Toast.makeText(applicationContext, "OK", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "NO OK", Toast.LENGTH_LONG).show()
            try {
                outputStream!!.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }*/

    /*private fun createXlsx(path: OutputStreamWriter, message: String) {
        try {
            val workbook = XSSFWorkbook()

            val outputStream = FileOutputStream(File(path, "/poi.xlsx"))

            val sheet = workbook.createSheet("Sheet 1")
            val row = sheet.createRow(2)
            val cell = row.createCell(1)
            cell.setCellValue(message)

            workbook.write(outputStream)
            outputStream.close()
            Toast.makeText(this, "poi.xlsx was successfully created", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }*/

    /*fun guardar(){

        val COLUMNs = arrayOf<String>("Id", "Name", "Address", "Age")
        val customers = Arrays.asList(
            Customer("1", "Jack Smith", "Massachusetts", 23),
            Customer("2", "Adam Johnson", "New York", 27),
            Customer("3", "Katherin Carter", "Washington DC", 26),
            Customer("4", "Jack London", "Nevada", 33),
            Customer("5", "Jason Bourne", "California", 36))

        val workbook = XSSFWorkbook()
        val createHelper = workbook.getCreationHelper()

        val sheet = workbook.createSheet("Customers")

        val headerFont = workbook.createFont()
        headerFont.setBold(true)
        headerFont.setColor(IndexedColors.BLUE.getIndex())

        val headerCellStyle = workbook.createCellStyle()
        headerCellStyle.setFont(headerFont)

        // Row for Header
        val headerRow = sheet.createRow(0)

        // Header
        for (col in COLUMNs.indices) {
            val cell = headerRow.createCell(col)
            cell.setCellValue(COLUMNs[col])
            cell.setCellStyle(headerCellStyle)
        }

        // CellStyle for Age
        val ageCellStyle = workbook.createCellStyle()
        ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"))

        var rowIdx = 1
        for (customer in customers) {
            val row = sheet.createRow(rowIdx++)
            row.createCell(0).setCellValue(customer.id)
            row.createCell(1).setCellValue(customer.name)
            row.createCell(2).setCellValue(customer.address)
            val ageCell = row.createCell(3)
            ageCell.setCellValue(customer.age.toDouble())
            ageCell.setCellStyle(ageCellStyle)
        }

        val fileOut = FileOutputStream("customers.xlsx")
        workbook.write(fileOut)
        fileOut.close()
        workbook.close()
    }*/

}