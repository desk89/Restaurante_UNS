package com.example.restauranteuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_generar_tracking.*
import kotlinx.android.synthetic.main.activity_registrarse.*

class GenerarTrackingActivity : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar_tracking)
        val trackingIntent: Intent=intent
        var tracking = trackingIntent.getStringExtra("nro")
        //Sobreescribimos el texto para mostrar el tracking
        textoTracking.text=tracking

        botonVolverInicio.setOnClickListener {
            startActivity(Intent(this,PrincipalCliente::class.java))
        }

    }
}