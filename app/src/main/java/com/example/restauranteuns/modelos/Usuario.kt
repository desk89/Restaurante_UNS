package com.example.restauranteuns.modelos
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
  data class Usuario(val id: String,val nombre: String,val email: String
                     ,val contraseña: String) {



}