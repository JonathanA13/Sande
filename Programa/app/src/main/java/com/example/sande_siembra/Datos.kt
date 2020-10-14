package com.example.sande_siembra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sande_siembra.modelo.DatosSiembra
import com.example.sande_siembra.modelo.Siembra
import kotlinx.android.synthetic.main.activity_datos.*
import java.util.EnumSet.of
import java.util.List.of

class Datos : AppCompatActivity(), RecyclerAdapter.OnDatosSiembraClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val listaDatosSiembra = listOf(
            DatosSiembra(
                10, "Vermer", "Vermer", "Vermer",
                "Vermer", "Vermer", "Vermer", 15, 20, 25, "Vermer",
                15, "Vermer", "Vermer", "Vermer", "Vermer", "12 Oct 2020", 15,
                "Vermer", 12, 5, "Vermer", "Vermer"
            ),
            DatosSiembra(
                10, "Vermer", "Vermer", "Vermer",
                "Vermer", "Vermer", "Vermer", 15, 20, 25, "Vermer",
                15, "Vermer", "Vermer", "Vermer", "Vermer", "12 Oct 2020", 15,
                "Vermer", 12, 5, "Vermer", "Vermer"
            ),
            DatosSiembra(
                10, "Vermer", "Vermer", "Vermer",
                "Vermer", "Vermer", "Vermer", 15, 20, 25, "Vermer",
                15, "Vermer", "Vermer", "Vermer", "Vermer", "12 Oct 2020", 15,
                "Vermer", 12, 5, "Vermer", "Vermer"
            ),
            DatosSiembra(
                10, "Vermer", "Vermer", "Vermer",
                "Vermer", "Vermer", "Vermer", 15, 20, 25, "Vermer",
                15, "Vermer", "Vermer", "Vermer", "Vermer", "12 Oct 2020", 15,
                "Vermer", 12, 5, "Vermer", "Vermer"
            ),
            DatosSiembra(
                10, "Vermer", "Vermer", "Vermer",
                "Vermer", "Vermer", "Vermer", 15, 20, 25, "Vermer",
                15, "Vermer", "Vermer", "Vermer", "Vermer", "12 Oct 2020", 15,
                "Vermer", 12, 5, "Vermer", "Vermer"
            ),
            DatosSiembra(
                10, "Vermer", "Vermer", "Vermer",
                "Vermer", "Vermer", "Vermer", 15, 20, 25, "Vermer",
                15, "Vermer", "Vermer", "Vermer", "Vermer", "12 Oct 2020", 15,
                "Vermer", 12, 5, "Vermer", "Vermer"
            ),
            DatosSiembra(
                10, "Vermer", "Vermer", "Vermer",
                "Vermer", "Vermer", "Vermer", 15, 20, 25, "Vermer",
                15, "Vermer", "Vermer", "Vermer", "Vermer", "12 Oct 2020", 15,
                "Vermer", 12, 5, "Vermer", "Vermer"
            )
        )
        recyclerView.adapter = RecyclerAdapter(this,listaDatosSiembra, this)
    }

    override fun onItemClick(valvulaGeneral: Int, semanaGeneral1: Int, semanaCabe: Int) {
        Log.i("click", "Lo que eligio es: ${valvulaGeneral}, ${semanaGeneral1}, ${semanaCabe}")
    }


}