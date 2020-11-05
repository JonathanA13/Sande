package com.example.sande_siembra.modelo

class Cabecera1 (
    var fecha: String,
    var cama: String,
    var prueba1: String,
    var prueba2: String,
    var origen: String,
    var variedad: String,
    var tipoSiembra: String,
    var color: String,
    var fincaGeneral1: String,
    var bloqueGeneral1: String,
    var etiquetaGeneral1: String,

    var procedimiento: String,
    var calibre: String,
    var semanaGeneral1: String,

    var metros: String,
    var bulbos: String,

    var semanaCabe: String,
    var bloqueCabe: String,
    var fincaCabe: String,

    var tamanioCama: String,
    var brote: String,
    var otraPrueba: String,
    var valvulaGeneral: String,
    var ladoGeneral1: String

) {
    override fun toString ():String{
        return "${fecha},${cama},${prueba1},${prueba2},${origen}" +
                ",${variedad},${tipoSiembra}, ${color},${fincaGeneral1},${bloqueGeneral1}" +
                ",${etiquetaGeneral1},${procedimiento},${calibre},${semanaGeneral1},${metros}" +
                ",${bulbos},${semanaCabe},${bloqueCabe},${fincaCabe},${tamanioCama}" +
                ",${brote},${otraPrueba},${valvulaGeneral},${ladoGeneral1}\n"
    }

}