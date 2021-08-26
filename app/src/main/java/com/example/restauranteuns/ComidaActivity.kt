package com.example.restauranteuns

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_comida.*
import java.util.*


class ComidaActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var fusedLocationClient: FusedLocationProviderClient

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comida)
        val emailIntent: Intent=intent
        var email = emailIntent.getStringExtra("email")

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        botonCeviche.setOnClickListener {
            val plato = "Ceviche"
            var cantidad = editCantidadComidad.text.toString()
            val precio = "20"
            var total:Int = 20 * editCantidadComidad.text.toString().toInt()
            var nro= valorRandom(1000..9999999)
            db.collection("pedidos").document(nro.toString()).set(
                hashMapOf("usuario" to email,"plato" to plato,"cantidad" to cantidad,
                    "precioUnitario" to precio,"total" to total,"nroTracking" to nro,
                    "repartidor" to "diego@gmail.com"))
                pedirPermisos(nro.toString())
                startActivity(Intent(this,GenerarTrackingActivity::class.java).putExtra(
                            "nro",nro.toString()))
        }


        botonChaufa.setOnClickListener {
            val plato = "Chaufa"
            var cantidad = editCantidadComidad.text.toString()
            val precio = "15"
            var total:Int = 15 * editCantidadComidad.text.toString().toInt()
            var nro= valorRandom(1000..9999999)
            db.collection("pedidos").document(nro.toString()).set(
                hashMapOf("usuario" to email,"plato" to plato,"cantidad" to cantidad,
                    "precioUnitario" to precio,"total" to total,"nroTracking" to nro,
                    "repartidor" to "diego@gmail.com"))
            pedirPermisos(nro.toString())
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
                hashMapOf("usuario" to email,"plato" to plato,"cantidad" to cantidad,
                    "precioUnitario" to precio,"total" to total,"nroTracking" to nro,
                    "repartidor" to "diego@gmail.com"))
            pedirPermisos(nro.toString())
            startActivity(Intent(this,GenerarTrackingActivity::class.java).putExtra(
                "nro",nro.toString()))
        }

    }

    private fun pedirPermisos(nro: String) {
        val task = fusedLocationClient.lastLocation

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
                    return

                }
        task.addOnSuccessListener {
            if (it!=null){
                val latitudCl=it.latitude
                val longitudCl=it.longitude
                db.collection("vistaTracking").document(nro).set(
                    hashMapOf("latitudCl" to latitudCl,
                                "longitudCl" to longitudCl,
                                "latitudR" to -9.119681868738962,
                                "longitudR" to  -78.5135890952766)
                )
                Toast.makeText(applicationContext, "${it.latitude}${it.longitude}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Funcion para generar valores aleatorios
    fun valorRandom(valores: IntRange) : Int {
        var r = Random()
        var valorRandom = r.nextInt(valores.last - valores.first) + valores.first
        return valorRandom
    }


}