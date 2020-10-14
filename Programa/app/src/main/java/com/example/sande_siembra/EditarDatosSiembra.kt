package com.example.sande_siembra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sande_siembra.modelo.DatosSiembra
import kotlinx.android.synthetic.main.activity_editar_datos_siembra.*

class EditarDatosSiembra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_datos_siembra)
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
            )
        )
        //recyclerView.adapter = RecyclerAdapter(this,listaDatosSiembra, this)
    }


}

