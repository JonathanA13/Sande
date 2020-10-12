package com.example.sande_siembra

import android.util.Log
import com.example.sande_siembra.modelo.DatosSiembra

class ServicioBDDMemoria {

    companion object{
        var listaCabecera = arrayListOf<Cabecera>()
        var listaDatosSiembra = arrayListOf<DatosSiembra>()

        fun agregarCabecera (
            fecha : String,
            semana : Int?,
            valvula : Int?,
            bloque : Int?,
            lado : String,
            etiqueta : String,
            finca : String
        ) {
            this.listaCabecera.add(Cabecera(fecha,semana,valvula,bloque,lado,etiqueta,finca))
            Log.i("Lista", "La lista es: ${listaCabecera}")
        }

        fun agregarListaDatosSiembra (
            cama: Int, variedad: String, tipoSiembra: String, procedimiento: String,
            prueba1: String,  prueba2: String, fincaCabe: String, semanaCabe: Int,
            bloqueCabe: Int, metros: Int, calibre: String, bulbos: Int,
            tamanioCama: String, brote: String, origen: String, otraPrueba: String,
            fechaGeneral1: String, semanaGeneral1: Int, fincaGeneral1: String, valvulaGeneral: Int,
            bloqueGeneral1: Int, ladoGeneral1: String, etiquetaGeneral1: String
        ) {
            this.listaDatosSiembra.add(
                DatosSiembra(cama, variedad,tipoSiembra,procedimiento,prueba1,prueba2,fincaCabe,semanaCabe,bloqueCabe,metros,
                    calibre, bulbos,tamanioCama,brote,origen,otraPrueba,fechaGeneral1,semanaGeneral1,fincaGeneral1, valvulaGeneral,
                    bloqueGeneral1,ladoGeneral1,etiquetaGeneral1)
            )
        }
    }
}