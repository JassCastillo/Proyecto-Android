package com.jass.proyecto

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

    fun getRegistro(): ArrayList<Registro>{
        val item:ArrayList<Registro> = ArrayList()

        //abrir DB en modo escritura
        val db: SQLiteDatabase = helper?.readableDatabase!!

        //Especificar columnas que quiero consultar
        val columnas = arrayOf(RegistroContract.Companion.Entrada.Columna_Id,RegistroContract.Companion.Entrada.Columna_Precio,RegistroContract.Companion.Entrada.Columna_Total,RegistroContract.Companion.Entrada.Columna_Cantidad,RegistroContract.Companion.Entrada.Columna_NombreProducto)

        //crear un cursor para recorre la tabla
        val c: Cursor= db.query(
            RegistroContract.Companion.Entrada.Nombre_Tabla,
            columnas,
            null,
            null,
            null,
            null,
            null
            )

        //hacer el recorrido del cursor en la tabla
        while (c.moveToNext()){
            item.add(
                Registro(
                c.getString(c.getColumnIndexOrThrow(RegistroContract.Companion.Entrada.Columna_Id)),
                    c.getString(c.getColumnIndexOrThrow(RegistroContract.Companion.Entrada.Columna_Precio)),
                    c.getString(c.getColumnIndexOrThrow(RegistroContract.Companion.Entrada.Columna_Total)),
                    c.getString(c.getColumnIndexOrThrow(RegistroContract.Companion.Entrada.Columna_Cantidad)),
                    c.getString(c.getColumnIndexOrThrow(RegistroContract.Companion.Entrada.Columna_NombreProducto))
            )
            )
        }

        //Cerrar DB

        db.close()

        return item
    }

    fun getRegistro(Id:String): Registro{

        var item:Registro?= null

        val db:SQLiteDatabase = helper?.readableDatabase!!
        val columnas = arrayOf(
                RegistroContract.Companion.Entrada.Columna_Id,
                RegistroContract.Companion.Entrada.Columna_Precio,
                RegistroContract.Companion.Entrada.Columna_Total,
                RegistroContract.Companion.Entrada.Columna_Cantidad,
                RegistroContract.Companion.Entrada.Columna_NombreProducto)

        val c:Cursor = db.query(
                RegistroContract.Companion.Entrada.Nombre_Tabla,
                columnas,
                " Id = ?",
                arrayOf(Id),
                null,
                null,
                null
        )

        while (c.moveToNext()){
            item = Registro(c.getString(c.getColumnIndexOrThrow( RegistroContract.Companion.Entrada.Columna_Id)),
                            c.getString(c.getColumnIndexOrThrow( RegistroContract.Companion.Entrada.Columna_Precio)),
                            c.getString(c.getColumnIndexOrThrow( RegistroContract.Companion.Entrada.Columna_Total)),
                            c.getString(c.getColumnIndexOrThrow( RegistroContract.Companion.Entrada.Columna_Cantidad)),
                            c.getString(c.getColumnIndexOrThrow( RegistroContract.Companion.Entrada.Columna_NombreProducto)))
        }
        c.close()

        return item!!;
    }
}
