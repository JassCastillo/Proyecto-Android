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
                val Columna_Nombres = "Nombres"
                val Columna_Apellidos = "Apellidos"
                val Columna_Telefono = "Telefono"
                val Columna_Direccion = "Direccion"
                val Columna_Cedula = "Cedula"
            }
        }
    }

}