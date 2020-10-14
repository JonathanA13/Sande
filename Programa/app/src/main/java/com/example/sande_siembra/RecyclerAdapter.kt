package com.example.sande_siembra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sande_siembra.base.BaseViewHolder
import com.example.sande_siembra.modelo.DatosSiembra
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerAdapter (
    private val context: Context,
    private val listaDatosSiembra: List<DatosSiembra>,
    private val itemClickListener: OnDatosSiembraClickListener
    //private val listaDatos:List<Compras>,
    //private val itemClickListener: OnComprasClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnDatosSiembraClickListener{
        fun onItemClick(
            valvulaGeneral: Int,
            semanaGeneral1: Int,
            semanaCabe: Int
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return DatosSiembraViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row,parent,false))
    }

    override fun getItemCount(): Int {
        return listaDatosSiembra.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is DatosSiembraViewHolder -> holder.bind(listaDatosSiembra[position],position)
            else -> throw IllegalArgumentException("El path es equivocado")
        }
    }

    inner class DatosSiembraViewHolder(itemView: View): BaseViewHolder<DatosSiembra>(itemView){
        override fun bind(item: DatosSiembra, position: Int) {
            itemView.setOnClickListener{ itemClickListener.onItemClick(item.valvulaGeneral, item.semanaGeneral1, item.semanaCabe)}
            itemView.txt_val.text = item.fechaGeneral1
            itemView.txt_blo.text = item.cama.toString()
        }
    }
}