package com.example.sande_siembra

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main_menu_inicio.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter


class MenuInicio : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_inicio)

        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl")
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl")
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl")

        //val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

        //File getDir ("Datos.xlsx", true)
        //File getFilesDir ()
        //val path = Environment.getStorageDirectory(Environment.DIRECTORY_DOCUMENTS)
        //val file = File(this.filesDir)

        val fout = OutputStreamWriter(
            openFileOutput("meminterna.xlsx", Context.MODE_PRIVATE)
        )

        fout.write("Contenido del fichero de memoria interna.")
        //fout.close()


        btnNuevo.setOnClickListener { botonNuevo() }
        //btn_sincro.setOnClickListener{ createXlsx(fout, "guardar") }
        //button2.setOnClickListener{ mostrarDialogoBasico() }
        button2.setOnClickListener{ irLista() }

        /*val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")*/

        /*var userId = 5
        FirebaseDatabase.getInstance().reference.child("usuario").child("1").setValue(userId)*/

        /*val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")*/



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
            Datos::class.java
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
        startActivity(intentExplicito)
    }

    fun prueba(){}
    fun sincronizar(){

        val fecha = "Sincronizacion"
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
        )
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