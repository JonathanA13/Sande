package com.example.sande_siembra

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sande_siembra.R

class SQLViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txtViewBloque: TextView = itemView.findViewById(R.id.txtViewBloqueRecycler)
    var txtViewCama: TextView = itemView.findViewById(R.id.txtViewCamaRecycler)
    var txtViewVariedad: TextView = itemView.findViewById(R.id.txtViewVariedadRecycler)
    var txtViewTipoSiembra: TextView = itemView.findViewById(R.id.txtViewTipoSIembraRecycler)
    var txtViewMetros: TextView = itemView.findViewById(R.id.txtViewMetrosRecycler)
    var txtViewCalibre: TextView = itemView.findViewById(R.id.txtViewCalibreRecycler)

    var imagenEditar: ImageView = itemView.findViewById(R.id.imagenEditar)


   // var editContact: ImageView = itemView.findViewById(R.id.editContact)

}