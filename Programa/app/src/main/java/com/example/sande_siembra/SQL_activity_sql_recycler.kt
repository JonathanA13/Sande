package com.example.sande_siembra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SQL_activity_sql_recycler : AppCompatActivity() {
    //private lateinit var dataBase: SqliteDatabase
    val context = this
    //val dataBase = SqliteDatabase(context)

    //para autocimpletar nombre


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sql_recycler)
        title = "KotlinApp"


        //para autocimpletar nombre
        /* val autoTextView: AutoCompleteTextView = findViewById(R.id.enterName)
         val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this,
             android.R.layout.select_dialog_item, nombres)
         autoTextView.threshold = 1
         autoTextView.setAdapter(adapter)*/


        val admin = AdminSQLiteOpenHelper(this@SQL_activity_sql_recycler, "SIEMBRA_BDD", null, 5)
        val contactView: RecyclerView = findViewById(R.id.myContactList)
        val linearLayoutManager = LinearLayoutManager(this)
        contactView.layoutManager = linearLayoutManager
        contactView.setHasFixedSize(true)
        admin.listSiembra()


        // dataBase = SqliteDatabase(<Contacts>= dataBase.listContacts()

        if (admin.listSiembra().size>0) {
            contactView.visibility = View.VISIBLE
            val mAdapter = SQLSiembraDatosAdapter(this,admin.listSiembra())
            //val mAdapter1 = ContactAdapter(this, ArrayList<Contacts>)
            contactView.adapter = mAdapter
        }
        else {
            contactView.visibility = View.GONE
            Toast.makeText(
                this,
                "There is no contact in the database. Start adding now",
                Toast.LENGTH_LONG
            ).show()
        }
        val btnAdd: Button = findViewById(R.id.btnAdd)
        //btnAdd.setOnClickListener { addTaskDialog() }
    }


    /*
    private fun addTaskDialog() {
        val inflater = LayoutInflater.from(this)
        val subView = inflater.inflate(R.layout.add_contacts, null)
        val nameField: EditText = subView.findViewById(R.id.enterName)
        val noField: EditText = subView.findViewById(R.id.enterPhoneNum)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add new CONTACT")
        builder.setView(subView)
        builder.create()
        builder.setPositiveButton("ADD CONTACT") { _, _ ->
            val name = nameField.text.toString()
            val phoneNum = noField.text.toString()
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong. Check your input values",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                val newContact = Contacts(name, phoneNum)
                dataBase.addContacts(newContact)
                finish()
                Toast.makeText(
                    this@MainActivity,
                    "Contacto guardado",
                    Toast.LENGTH_LONG
                ).show()
                startActivity(intent)
            }
        }
        builder.setNegativeButton("CANCEL") { _, _ -> Toast.makeText(this@MainActivity, "Task cancelled",
            Toast.LENGTH_LONG).show()}
        builder.show()
    }
    override fun onDestroy() {
        super.onDestroy()
        dataBase.close()
    }*/
}