package com.jass.proyecto

class Registro(Id:String, Nombres:String, Apellidos:String, Telefono:String, Direccion: String, Cedula:String) {
    private var Id:String? = null;
    private var Nombres:String? = null;
    private var Apellidos:String? = null;
    private var Telefono:String? = null;
    private var Direccion:String? = null;
    private var Cedula:String? = null;

    init {
        this.Id = Id;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.Cedula = Cedula;
    }
}