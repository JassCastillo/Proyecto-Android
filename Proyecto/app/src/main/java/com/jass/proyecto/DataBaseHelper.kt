package com.jass.proyecto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBaseHelper(context: Context): SQLiteOpenHelper(context,RegistroContract.Companion.Entrada.Nombre_Tabla, null,RegistroContract.Companion.VERSION)
{
    companion object{
        val CREATE_USUARIOS_TABLA = "CREATE TABLE" + RegistroContract.Companion.Entrada.Nombre_Tabla +
                " (" + RegistroContract.Companion.Entrada.Columna_Id + "TEXT PRIMARY KEY," +
                RegistroContract.Companion.Entrada.Columna_Precio + "TEXT" +
                RegistroContract.Companion.Entrada.Columna_Total + "TEXT" +
                RegistroContract.Companion.Entrada.Columna_Cantidad + "TEXT" +
                RegistroContract.Companion.Entrada.Columna_NombreProducto + "TEXT)"
        val REMOVE_USUARIOS_TABLA = "DROP TABLE IF EXIST " + RegistroContract.Companion.Entrada.Nombre_Tabla
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USUARIOS_TABLA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, i: Int, i2: Int) {
        db?.execSQL(REMOVE_USUARIOS_TABLA)
        onCreate(db)
    }
}