package com.example.sande_siembra

class Cabecera (
    var fecha : String,
    var semana : Int?,
    var valvula : Int?,
    val bloque : Int?,
    val lado : String,
    val etiqueta : String
) {

    override fun toString(): String {
        return "${fecha} ${semana} ${valvula} ${bloque} ${lado} ${etiqueta}"
    }

}