package com.example.sande_siembra.modelo

class DatosSiembra (

     var fecha: String,
     var cama: Int,
     var prueba1: String,
     var prueba2: String,
     var origen: String,
     var variedad: String,
     var tipoSiembra: String,
     var color: String,
     var fincaGeneral1: String,
     var bloqueGeneral1: Int,
     var etiquetaGeneral1: String,
     var procedimiento: String,
     var calibre: String,
     var semanaGeneral1: Int,
     var metros: Int,
     var bulbos: Int,
     var semanaCabe: Int,
     var bloqueCabe: Int,
     var fincaCabe: String,
     var tamanioCama: String,
     var brote: String,
     var otraPrueba: String,
     var valvulaGeneral: Int,
     var ladoGeneral1: String,
     var codigo: String


    ) {
    override fun toString ():String{
         return "${fecha},${cama},${prueba1},${prueba2},${origen}" +
                 ",${variedad},${tipoSiembra}, ${color},${fincaGeneral1},${bloqueGeneral1}" +
                 ",${etiquetaGeneral1},${procedimiento},${calibre},${semanaGeneral1},${metros}" +
                 ",${bulbos},${semanaCabe},${bloqueCabe},${fincaCabe},${tamanioCama}" +
                 ",${brote},${otraPrueba},${valvulaGeneral},${ladoGeneral1},${codigo}\n"
    }

}

/*override fun toString ():String{
         return "${fechaGeneral1};${cama};${prueba1};${prueba2};${origen};${variedad};${fincaGeneral1}" +
                 ";${bloqueGeneral1};${tipoSiembra};${procedimiento};${calibre};${semanaGeneral1}" +
                 ";${metros};${bulbos};${semanaCabe};${bloqueCabe};${fincaCabe}" +
                 ";${tamanioCama};${brote};${otraPrueba}" +
                 ";${valvulaGeneral};${ladoGeneral1};${etiquetaGeneral1}\n"
    }*/