package com.example.restauranteuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_comida.*
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class ComidaActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comida)
        val emailIntent: Intent=intent
        var email = emailIntent.getStringExtra("email")

        botonCeviche.setOnClickListener {
            val plato = "Ceviche"
            var cantidad = editCantidadComidad.text.toString()
            val precio = "20"
            var total:Int = 20 * editCantidadComidad.text.toString().toInt()
            var nro= valorRandom(1000..9999999)
            db.collection("pedidos").document(nro.toString()).set(
                hashMapOf("usuario" to email,
                    "plato" to plato,
                    "cantidad" to cantidad,
                    "precioUnitario" to precio,
                    "total" to total,
                    "nroTracking" to nro,
                    "repartidor" to "anghelo@gmail.com")
            )
            startActivity(Intent(this,GenerarTrackingActivity::class.java).putExtra(
                "nro",nro.toString()
            ))
        }

        botonChaufa.setOnClickListener {
            val plato = "Chaufa"
            var cantidad = editCantidadComidad.text.toString()
            val precio = "15"
            var total:Int = 15 * editCantidadComidad.text.toString().toInt()
            var nro= valorRandom(1000..9999999)
            db.collection("pedidos").document(nro.toString()).set(
                hashMapOf("usuario" to email,
                    "plato" to plato,
                    "cantidad" to cantidad,
                    "precioUnitario" to precio,
                    "total" to total,
                    "nroTracking" to nro,
                    "repartidor" to "anghelo@gmail.com")
            )
            startActivity(Intent(this,GenerarTrackingActivity::class.java).putExtra(
                "nro",nro.toString()))
        }

        botonPollo.setOnClickListener {
            val plato = "Pollo a la brasa"
            var cantidad = editCantidadComidad.text.toString()
            val precio = "18"
            var total:Int = 18 * editCantidadComidad.text.toString().toInt()
            var nro= valorRandom(1000..9999999)
            db.collection("pedidos").document(nro.toString()).set(
                hashMapOf("usuario" to email,
                    "plato" to plato,
                    "cantidad" to cantidad,
                    "precioUnitario" to precio,
                    "total" to total,
                    "nroTracking" to nro,
                    "repartidor" to "anghelo@gmail.com")
            )
            startActivity(Intent(this,GenerarTrackingActivity::class.java).putExtra(
                "nro",nro.toString()))
        }

    }

    //Funcion para generar valores aleatorios
    fun valorRandom(valores: IntRange) : Int {
        var r = Random()
        var valorRandom = r.nextInt(valores.last - valores.first) + valores.first
        return valorRandom
    }

}