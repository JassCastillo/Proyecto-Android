package com.jass.proyecto

import android.os.Build
import android.provider.BaseColumns

class RegistroContract {

    companion object{
        val VERSION = 1
        class Entrada: BaseColumns{
            companion object{
                val Nombre_Tabla = "Usuarios"
                val Columna_Id = "Id"
                val Columna_Precio = "Precio"
                val Columna_Total = "Total"
                val Columna_Cantidad = "Cantidad"
                val Columna_NombreProducto = "NombreProducto"
            }
        }
    }

}