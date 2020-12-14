package com.jass.proyecto

class Registro(Id:String, Precio:String, Cantidad:String, Total:String, NombreProducto: String) {
     var Id:String? = null;
     var Precio:String? = null;
     var Cantidad:String? = null;
     var Total:String? = null;
     var NombreProducto:String? = null;


    init {
        this.Id = Id;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Total = Total;
        this.NombreProducto = NombreProducto;

    }
}