package com.example.sande_siembra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sande_siembra.modelo.Siembra
import kotlinx.android.synthetic.main.item_row.view.*

class MainAdapter (private val context:Context): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<Siembra>()

    fun setListData(data: MutableList<Siembra>){
        dataList = data
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(siembra: Siembra){
            itemView.txt_val.text = "Valvula: ${siembra.valvula.toString()}"
            itemView.txt_blo.text = "Bloque: ${siembra.bloque.toString()}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(dataList.size > 0){
            dataList.size
        } else{
            0
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val siembra1: Siembra = dataList[position]
        holder.bindView(siembra1)
    }
}