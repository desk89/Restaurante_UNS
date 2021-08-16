package com.example.restauranteuns.modelos

  class Usuario {
    var id: String=""
    var nombre: String
    var contraseña: String
    var tipo: String

      constructor(nombre: String,contraseña: String,tipo: String){
        this.nombre=nombre
          this.contraseña=contraseña
          this.tipo=tipo
      }

}