package com.example.sande_siembra.modelo

class DatosSiembra (
     var cama: Int,
     var variedad: String,
     var tipoSiembra: String,
     var procedimiento: String,
     var prueba1: String,
     var prueba2: String,
     var fincaCabe: String,
     var semanaCabe: Int,
     var bloqueCabe: Int,
     var metros: Int,
     var calibre: String,
     var bulbos: Int,
     var tamanioCama: String,
     var brote: String,
     var origen: String,
     var otraPrueba: String,
     var fechaGeneral1: String,
     var semanaGeneral1: Int,
     var fincaGeneral1: String,
     var valvulaGeneral: Int,
     var bloqueGeneral1: Int,
     var ladoGeneral1: String,
     var etiquetaGeneral1: String
    ) {

    override fun toString ():String{
        return "${cama} ${variedad} ${tipoSiembra} ${procedimiento} ${prueba1} ${prueba2} ${fincaCabe} ${semanaCabe} ${bloqueCabe} ${metros}" +
                "${calibre} ${bulbos} ${tamanioCama} ${brote} ${origen} ${otraPrueba} ${fechaGeneral1} ${semanaGeneral1} " +
                "${fincaGeneral1} ${bloqueGeneral1} ${valvulaGeneral} ${ladoGeneral1} ${etiquetaGeneral1}"
    }
}