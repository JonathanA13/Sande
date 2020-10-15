package com.example.sande_siembra


import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.sande_siembra.modelo.RegistroSiembra
import java.util.*
internal class SQLSiembraDatosAdapter(private val context: Context, listSiembra: ArrayList<RegistroSiembra>) :
    RecyclerView.Adapter<SQLViewHolder>(), Filterable {
    private var listSiembra: ArrayList<RegistroSiembra>
    private val mArrayList: ArrayList<RegistroSiembra>
    //private val mDatabase: AdminSQLiteOpenHelper
    init {
        this.listSiembra = listSiembra
        this.mArrayList = listSiembra
        //mDatabase = SqliteDatabase(context)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SQLViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sql_recycler_siembra, parent, false)
        return SQLViewHolder(view)
    }
    override fun onBindViewHolder(holder: SQLViewHolder, position: Int) {
        val contacts = listSiembra[position]

        holder.txtViewBloque.text= contacts.bloque.toString()
        holder.txtViewCama.text= contacts.cama.toString()
        holder.txtViewVariedad.text=contacts.variedad
        holder.txtViewTipoSiembra.text=contacts.tiposiembra
        holder.txtViewMetros.text=contacts.metros.toString()
        holder.txtViewCalibre.text=contacts.calibre

        holder.imagenEditar.setOnClickListener{}


       // holder.editContact.setOnClickListener { editTaskDialog(contacts) }
        /*
        holder.deleteContact.setOnClickListener {
            mDatabase.deleteContact(contacts.id)
            (context as Activity).finish()
            context.startActivity(context.intent)
        } */
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                listSiembra = if (charString.isEmpty()) {
                    mArrayList
                }
                else {
                    val filteredList = ArrayList<RegistroSiembra>()
                    for (contacts in mArrayList) {
                        /*if (contacts.name.toLowerCase().contains(charString)) {
                            filteredList.add(contacts)
                        }*/
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = listSiembra
                return filterResults
            }
            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            )
            {
                listSiembra =
                    filterResults.values as ArrayList<RegistroSiembra>
                notifyDataSetChanged()
            }
        }
    }
    override fun getItemCount(): Int {
        return listSiembra.size
    }
    /*
    private fun editTaskDialog(contacts: Contacts) {
        val inflater = LayoutInflater.from(context)
        val subView = inflater.inflate(R.layout.add_contacts, null)
        val nameField: EditText = subView.findViewById(R.id.enterName)
        val contactField: EditText = subView.findViewById(R.id.enterPhoneNum)
        nameField.setText(contacts.name)
        contactField.setText(contacts.phno)
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit contact")
        builder.setView(subView)
        builder.create()
        builder.setPositiveButton(
            "EDIT CONTACT"
        ) { _, _ ->
            val name = nameField.text.toString()
            val phNo = contactField.text.toString()
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(
                    context,
                    "Something went wrong. Check your input values",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                mDatabase.updateContacts(
                    Contacts(
                        Objects.requireNonNull<Any>(contacts.id) as Int,
                        name,
                        phNo
                    )
                )
                (context as Activity).finish()
                context.startActivity(context.intent)
            }
        }
        builder.setNegativeButton(
            "CANCEL"
        ) { _, _ -> Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show() }
        builder.show()
    } */
}

