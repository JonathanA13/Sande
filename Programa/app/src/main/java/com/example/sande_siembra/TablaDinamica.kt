package com.example.sande_siembra

import android.app.Activity
import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Rect
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView


class TablaDinamica(
    recuperarDatos: RecuperarDatos,
    tableLayout: TableLayout
) {
    private var tabla: TableLayout? = null
    private var filas: ArrayList<TableRow>? = null
    private var actividad: Activity? = null
    private var rs: Resources? = null
    private var FILAS = 0
    private  var COLUMNAS:Int = 0


    fun Tabla(actividad: Activity?, tabla: TableLayout?) {
        this.actividad = actividad
        this.tabla = tabla
        rs = this.actividad!!.resources
        COLUMNAS = 0
        FILAS = COLUMNAS
        filas = ArrayList<TableRow>()
    }

    /**
     * Añade la cabecera a la tabla
     * @param recursocabecera Recurso (array) donde se encuentra la cabecera de la tabla
     */
    fun agregarCabecera(recursocabecera: Int) {
        var layoutCelda: TableRow.LayoutParams?
        val fila = TableRow(actividad)
        val layoutFila: TableRow.LayoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        fila.setLayoutParams(layoutFila)
        val arraycabecera: Array<String> = rs?.getStringArray(recursocabecera) as Array<String>
        COLUMNAS = arraycabecera.size
        for (i in arraycabecera.indices) {
            val texto = TextView(actividad)
            layoutCelda = TableRow.LayoutParams(
                obtenerAnchoPixelesTexto(arraycabecera[i]),
                TableRow.LayoutParams.WRAP_CONTENT
            )
            texto.text = arraycabecera[i]
            texto.gravity = Gravity.CENTER_HORIZONTAL
            //texto.setTextAppearance(actividad, R.style.estilo_celda)
            //texto.setBackgroundResource(R.drawable.tabla_celda_cabecera)
            texto.layoutParams = layoutCelda
            fila.addView(texto)
        }
        tabla!!.addView(fila)
        filas!!.add(fila)
        FILAS++
    }

    /**
     * Agrega una fila a la tabla
     * @param elementos Elementos de la fila
     */
    fun agregarFilaTabla(elementos: ArrayList<String>) {
        var layoutCelda: TableRow.LayoutParams?
        val layoutFila: TableRow.LayoutParams =
            TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
        val fila = TableRow(actividad)
        fila.setLayoutParams(layoutFila)
        for (i in 0 until elementos.size) {
            val texto = TextView(actividad)
            texto.text = java.lang.String.valueOf(elementos[i])
            texto.gravity = Gravity.CENTER_HORIZONTAL
            //texto.setTextAppearance(actividad, R.style.estilo_celda)
            //texto.setBackgroundResource(R.drawable.tabla_celda)
            layoutCelda = TableRow.LayoutParams(
                obtenerAnchoPixelesTexto(texto.text.toString()),
                TableRow.LayoutParams.WRAP_CONTENT
            )
            texto.layoutParams = layoutCelda
            fila.addView(texto)
        }
        tabla!!.addView(fila)
        filas!!.add(fila)
        FILAS++
    }

    /**
     * Elimina una fila de la tabla
     * @param indicefilaeliminar Indice de la fila a eliminar
     */
    fun eliminarFila(indicefilaeliminar: Int) {
        if (indicefilaeliminar > 0 && indicefilaeliminar < FILAS) {
            tabla!!.removeViewAt(indicefilaeliminar)
            FILAS--
        }
    }

    /**
     * Devuelve las filas de la tabla, la cabecera se cuenta como fila
     * @return Filas totales de la tabla
     */
    fun getFilas(): Int {
        return FILAS
    }

    /**
     * Devuelve las columnas de la tabla
     * @return Columnas totales de la tabla
     */
    fun getColumnas(): Int {
        return COLUMNAS
    }

    /**
     * Devuelve el número de celdas de la tabla, la cabecera se cuenta como fila
     * @return Número de celdas totales de la tabla
     */
    fun getCeldasTotales(): Int {
        return FILAS * COLUMNAS
    }

    private fun obtenerAnchoPixelesTexto(texto: String): Int {
        val p = Paint()
        val bounds = Rect()
        p.setTextSize(50F)
        p.getTextBounds(texto, 0, texto.length, bounds)
        return bounds.width()
    }

}