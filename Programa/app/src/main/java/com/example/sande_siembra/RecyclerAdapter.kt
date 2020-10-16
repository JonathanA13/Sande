package com.example.sande_siembra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sande_siembra.base.BaseViewHolder
import com.example.sande_siembra.modelo.DatosSiembra
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerAdapter(
    private val context: Context,
    private val listaDatosSiembra: List<DatosSiembra>,
    private val itemClickListener: OnDatosSiembraClickListener
    //private val listaDatos:List<Compras>,
    //private val itemClickListener: OnComprasClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

   //todos los parametros asi no se presenten


    interface OnDatosSiembraClickListener {

        fun onItemClick(
            position: Int,
            cama: Int,
            variedad: String,
            tipoSiembra: String,
            procedimiento: String,
            prueba1: String,
            prueba2: String,
            fincaCabe: String,
            semanaCabe: Int,
            bloqueCabe: Int,
            metros: Int,
            calibre: String,
            bulbos: Int,
            tamanioCama: String,
            brote: String,
            origen: String,
            otraPrueba: String,
            fechaGeneral1: String,
            semanaGeneral1: Int,
            fincaGeneral1: String,
            valvulaGeneral: Int,
            bloqueGeneral1: Int,
            ladoGeneral1: String,
            etiquetaGeneral1: String
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return DatosSiembraViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listaDatosSiembra.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is DatosSiembraViewHolder -> holder.bind(listaDatosSiembra[position], position)
            else -> throw IllegalArgumentException("El path es equivocado")
        }
    }

    inner class DatosSiembraViewHolder(itemView: View) : BaseViewHolder<DatosSiembra>(itemView) {
        override fun bind(item: DatosSiembra, position: Int) {


            //solo los que presenta en la pantalla

            itemView.setOnClickListener {
                itemClickListener.onItemClick(
                    position, item.cama, item.variedad,
                    item.tipoSiembra,
                    item.procedimiento,
                    item.prueba1,
                    item.prueba2,
                    item.fincaCabe,
                    item.semanaCabe,
                    item.bloqueCabe,
                    item.metros,
                    item.calibre,
                    item.bulbos,
                    item.tamanioCama,
                    item.brote,
                    item.origen,
                    item.otraPrueba,
                    item.fechaGeneral1,
                    item.semanaGeneral1,
                    item.fincaGeneral1,
                    item.valvulaGeneral,
                    item.bloqueGeneral1,
                    item.ladoGeneral1,
                    item.etiquetaGeneral1
                )
            }

            itemView.txt_blo.text = item.bloqueGeneral1.toString()
            itemView.txt_cama.text = item.cama.toString()
            itemView.txt_variedad.text = item.variedad
            itemView.txt_tipo.text = item.tipoSiembra
            itemView.txt_metro.text = item.metros.toString()
            itemView.txt_calibre.text = item.calibre

        }
    }
}