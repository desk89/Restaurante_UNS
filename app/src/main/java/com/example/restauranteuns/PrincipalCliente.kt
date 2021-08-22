package com.example.restauranteuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_principal_cliente.*


class PrincipalCliente : AppCompatActivity() {

    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_cliente)
        //Boton Salir
        val objetoIntent: Intent=intent
        var emailUsuario = objetoIntent.getStringExtra("emailLogin")
        quedesea.text=emailUsuario
        botonSalir.setOnClickListener {
            //Regreso al login
            val intentoLogin= Intent(this,MainActivity::class.java)
            startActivity(intentoLogin)
        }

        botonPedido.setOnClickListener {

        }

        botonTrackingCliente.setOnClickListener {

        }

    }
}