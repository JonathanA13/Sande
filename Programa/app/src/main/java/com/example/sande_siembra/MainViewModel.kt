package com.example.sande_siembra

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sande_siembra.modelo.Siembra

class MainViewModel: ViewModel() {

    private val repo = Repo()

    fun fetchSiembraData(): LiveData<MutableList<Siembra>>{
        val mutableData = MutableLiveData<MutableList<Siembra>>()
        repo.getSiembraData().observeForever{ siembraList ->
            mutableData.value = siembraList
        }
        return mutableData
    }
}