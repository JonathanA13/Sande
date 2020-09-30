package com.example.sande_siembra

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sande_siembra.modelo.Siembra
import com.google.firebase.firestore.FirebaseFirestore

class Repo {

    fun getSiembraData(): LiveData<MutableList<Siembra>>{
        val mutableData = MutableLiveData<MutableList<Siembra>>()
        FirebaseFirestore.getInstance().collection("SiembraPrueba").get().addOnSuccessListener {resultado ->
            val listData = mutableListOf<Siembra>()
            for (document in resultado){
                val bloque = document.get("Bloque").toString().toInt()
                val valvula = document.get("Valvula").toString().toInt()
                val siembra = Siembra(bloque,valvula)
                listData.add(siembra)
            }

            mutableData.value = listData
        }
        return mutableData
    }
}