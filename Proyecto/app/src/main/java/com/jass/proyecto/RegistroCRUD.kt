package com.jass.proyecto

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class RegistroCRUD(context: Context) {

    private var helper: DataBaseHelper? = null
    init {
        helper = DataBaseHelper(context)
    }

    fun newRegistro(item:Registro){
        //Abrir la BD en modo escritura
        val db:SQLiteDatabase = helper?.writableDatabase!!

      //mapeo de columnas con valores a insertar
        val values = ContentValues()
        values.put(RegistroContract.Companion.Entrada.Columna_Id,item.Id)
        values.put(RegistroContract.Companion.Entrada.Columna_Precio,item.Precio)
        values.put(RegistroContract.Companion.Entrada.Columna_Total,item.Total)
        values.put(RegistroContract.Companion.Entrada.Columna_Cantidad,item.Cantidad)
        values.put(RegistroContract.Companion.Entrada.Columna_NombreProducto,item.NombreProducto)

      //Insertar una nueva fila en la tabla
        val newRowId = db.insert(RegistroContract.Companion.Entrada.Nombre_Tabla, null, values)
        // Cerrar la BD
        db.close()
    }
}
