package com.example.sande_siembra

import android.util.Log

class ServicioBDDMemoria {

    companion object{
        var listaCabecera = arrayListOf<Cabecera>()

        fun agregarCabecera (
            fecha : String,
            semana : Int?,
            valvula : Int?,
            bloque : Int?,
            lado : String,
            etiqueta : String
        ) {
            this.listaCabecera.add(Cabecera(fecha,semana,valvula,bloque,lado,etiqueta))
            Log.i("Lista", "La lista es: ${listaCabecera}")
        }
    }
}