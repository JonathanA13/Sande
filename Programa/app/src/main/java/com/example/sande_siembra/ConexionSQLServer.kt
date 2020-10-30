package com.example.sande_siembra

import android.os.StrictMode
import android.util.Log
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConexionSQLServer {

    private val ip = "192.168.146.7:1433"
    private val db = "SandeSiembra"
    private val userName = "sa"
    private val password = "Sande01"

    fun dbCon() : Connection? {
        val policy =  StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn : Connection? = null
        var connString : String? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connString = "jdbc:jtds:sqlserver://$ip:databaseName=$db:user=$userName:password=$password:"
            conn = DriverManager.getConnection(connString)
        } catch (ex: SQLException){
            Log.e("Error: ", ex.message.toString())
        } catch (ex1: ClassNotFoundException) {
            Log.e("Error: ", ex1.message.toString())
        } catch (ex2: Exception){
            Log.e("Error: ", ex2.message.toString())
        }
        return conn
    }
}