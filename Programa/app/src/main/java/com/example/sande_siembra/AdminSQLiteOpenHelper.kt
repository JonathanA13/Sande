package com.example.sande_siembra


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sande_siembra.modelo.RegistroSiembra
import java.util.*

/*
class AdminSQLiteOpenHelper internal constructor(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    )*/


class AdminSQLiteOpenHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) :

    SQLiteOpenHelper(context, name, factory, version)

{
    override fun onCreate(db: SQLiteDatabase) {
        val createContactTable = ("CREATE TABLE "
                + TABLE_SIEMBRA + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_SEMANA + " INTEGER,"
                + COLUMN_ESPECIE + " TEXT,"
                + COLUMN_FINCA + " TEXT,"
                + COLUMN_BLOQUE + " INTEGER,"
                + COLUMN_VALVULA + " INTEGER,"
                + COLUMN_LADO + " TEXT,"
                + COLUMN_ETIQUETA + " TEXT,"
                + COLUMN_CAMA + " INTEGER,"
                + COLUMN_PROCE + " TEXT,"
                + COLUMN_TAMANIOCAMA + " TEXT,"
                + COLUMN_BROTE + " TEXT,"
                + COLUMN_TIPOSIEMBRA + " TEXT,"
                + COLUMN_VARIEDAD + " TEXT,"
                + COLUMN_ORIGEN + " TEXT,"
                + COLUMN_PRUEBAS1 + " TEXT,"
                + COLUMN_PRUEBAS2 + " TEXT,"
                + COLUMN_OTRASPRUEBAS + " TEXT,"
                + COLUMN_SEMANACABE + " INTEGER,"
                + COLUMN_FINCACABE + " TEXT,"
                + COLUMN_BLOQUECABE + " INTEGER,"
                + COLUMN_METROS + " INTEGER,"
                + COLUMN_CALIBRE + " TEXT,"
                + COLUMN_BULBOS + " INTEGER" + ")")
        db.execSQL(createContactTable)
    }
    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SIEMBRA")
        onCreate(db)
    }
    fun listSiembra(): ArrayList<RegistroSiembra> {
        val sql = "select * from $TABLE_SIEMBRA"
        val db = this.readableDatabase
        val storeSiembra = ArrayList<RegistroSiembra>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val fecha = cursor.getString(1)
                val semana = cursor.getString(2).toInt()
                val especie = cursor.getString(3)
                val finca = cursor.getString(4)
                val bloque = cursor.getString(5).toInt()
                val valvula = cursor.getString(6).toInt()
                val lado = cursor.getString(7)
                val etiqueta = cursor.getString(8)
                val cama = cursor.getString(9).toInt()
                val proce = cursor.getString(10)
                val tamaniocama = cursor.getString(11)
                val brote = cursor.getString(12)
                val tiposiembra = cursor.getString(13)
                val variedad = cursor.getString(14)
                val origen = cursor.getString(15)
                val pruebas1 = cursor.getString(16)
                val pruebas2 = cursor.getString(17)
                val otraspruebas = cursor.getString(18)
                val semanacabe = cursor.getString(19).toInt()
                val fincacabe = cursor.getString(20)
                val bloquecabe = cursor.getString(21).toInt()
                val metros = cursor.getString(22).toInt()
                val calibre = cursor.getString(23)
                val bulbos = cursor.getString(24).toInt()


                storeSiembra.add(RegistroSiembra(id,fecha,semana,especie,finca,bloque,valvula,lado,etiqueta,cama,proce,tamaniocama,brote,tiposiembra,origen,variedad,pruebas1,pruebas2,otraspruebas,semanacabe,bloquecabe,fincacabe,metros,calibre,bulbos))
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        return storeSiembra
    }
    fun addSiembra(siembra: RegistroSiembra) {
        val values = ContentValues()
        values.put(COLUMN_DATE, siembra.fecha)
        values.put(COLUMN_SEMANA, siembra.semana)
        values.put(COLUMN_ESPECIE, siembra.especie)
        values.put(COLUMN_FINCA, siembra.finca)
        values.put(COLUMN_BLOQUE, siembra.bloque)
        values.put(COLUMN_VALVULA, siembra.valvula)
        values.put(COLUMN_LADO, siembra.lado)
        values.put(COLUMN_ETIQUETA, siembra.etiqueta)
        values.put(COLUMN_CAMA, siembra.cama)
        values.put(COLUMN_PROCE, siembra.proce)
        values.put(COLUMN_TAMANIOCAMA, siembra.tamaniocama)
        values.put(COLUMN_BROTE, siembra.brote)
        values.put(COLUMN_TIPOSIEMBRA, siembra.tiposiembra)
        values.put(COLUMN_VARIEDAD, siembra.variedad)
        values.put(COLUMN_ORIGEN, siembra.origen)
        values.put(COLUMN_PRUEBAS1, siembra.pruebas1)
        values.put(COLUMN_PRUEBAS2, siembra.pruebas2)
        values.put(COLUMN_OTRASPRUEBAS, siembra.otraspruebas)
        values.put(COLUMN_SEMANACABE, siembra.semanacabe)
        values.put(COLUMN_FINCACABE, siembra.fincacabe)
        values.put(COLUMN_BLOQUECABE, siembra.bloquecabe)
        values.put(COLUMN_METROS, siembra.metros)
        values.put(COLUMN_CALIBRE, siembra.calibre)
        values.put(COLUMN_BULBOS, siembra.bulbos)


        val db = this.writableDatabase
        db.insert(TABLE_SIEMBRA, null, values)
    }
    fun updateContacts(siembra: RegistroSiembra) {
        val values = ContentValues()
        values.put(COLUMN_DATE, siembra.fecha)
        values.put(COLUMN_SEMANA, siembra.semana)
        values.put(COLUMN_ESPECIE, siembra.especie)
        values.put(COLUMN_FINCA, siembra.finca)
        values.put(COLUMN_BLOQUE, siembra.bloque)
        values.put(COLUMN_VALVULA, siembra.valvula)
        values.put(COLUMN_LADO, siembra.lado)
        values.put(COLUMN_ETIQUETA, siembra.etiqueta)
        values.put(COLUMN_CAMA, siembra.cama)
        values.put(COLUMN_PROCE, siembra.proce)
        values.put(COLUMN_TAMANIOCAMA, siembra.tamaniocama)
        values.put(COLUMN_BROTE, siembra.brote)
        values.put(COLUMN_TIPOSIEMBRA, siembra.tiposiembra)
        values.put(COLUMN_VARIEDAD, siembra.variedad)
        values.put(COLUMN_ORIGEN, siembra.origen)
        values.put(COLUMN_PRUEBAS1, siembra.pruebas1)
        values.put(COLUMN_PRUEBAS2, siembra.pruebas2)
        values.put(COLUMN_OTRASPRUEBAS, siembra.otraspruebas)
        values.put(COLUMN_SEMANACABE, siembra.semanacabe)
        values.put(COLUMN_FINCACABE, siembra.fincacabe)
        values.put(COLUMN_BLOQUECABE, siembra.bloquecabe)
        values.put(COLUMN_METROS, siembra.metros)
        values.put(COLUMN_CALIBRE, siembra.calibre)
        values.put(COLUMN_BULBOS, siembra.bulbos)

        val db = this.writableDatabase
        db.update(
            TABLE_SIEMBRA,
            values,
            "$COLUMN_ID = ?",
            arrayOf(siembra.id.toString())
        )

    }
    fun deleteContact(id: Int) {
        val db = this.writableDatabase
        db.delete(
            TABLE_SIEMBRA,
            "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )
    }
    companion object {
        private const val DATABASE_VERSION = 5
        private const val DATABASE_NAME = "SIEMBRA_BDD"
        private const val TABLE_SIEMBRA = "SIEMBRA_TABLE"
        private const val COLUMN_ID = "id"
        private const val COLUMN_DATE = "DATE"
        private const val COLUMN_SEMANA = "SEMANA"
        private const val COLUMN_ESPECIE = "ESPECIE"
        private const val COLUMN_FINCA = "FINCA"
        private const val COLUMN_VALVULA = "VALVULA"
        private const val COLUMN_BLOQUE = "BLOQUE"
        private const val COLUMN_LADO = "LADO"
        private const val COLUMN_ETIQUETA = "ETIQUETA"
        private const val COLUMN_CAMA = "CAMA"
        private const val COLUMN_PROCE = "PROCE"
        private const val COLUMN_TAMANIOCAMA = "TAMANIOCAMA"
        private const val COLUMN_BROTE = "BROTE"
        private const val COLUMN_TIPOSIEMBRA = "TIPOSIEMBRA"
        private const val COLUMN_VARIEDAD = "VARIEDAD"
        private const val COLUMN_ORIGEN = "ORIGEN"
        private const val COLUMN_PRUEBAS1 = "PRUEBAS1"
        private const val COLUMN_PRUEBAS2 = "PRUEBAS2"
        private const val COLUMN_OTRASPRUEBAS = "OTRASPRUEBAS"
        private const val COLUMN_SEMANACABE = "SEMANACABE"
        private const val COLUMN_BLOQUECABE = "BLOQUECABE"
        private const val COLUMN_FINCACABE = "FINCACABE"
        private const val COLUMN_METROS = "METROS"
        private const val COLUMN_CALIBRE = "CALIBRE"
        private const val COLUMN_BULBOS = "BULBOS"

    }
}