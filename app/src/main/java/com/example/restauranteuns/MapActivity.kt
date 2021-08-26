package com.example.restauranteuns

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_map.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity() ,OnMapReadyCallback {
    val db = Firebase.firestore
    private lateinit var nroTrack: String
    private lateinit var mapa: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val trackingIntent: Intent=intent
        nroTrack = trackingIntent.getStringExtra("tracking").toString()
        tv_ubicacionmapa.text=nroTrack
        Fragmento1()
        btn_vlista.setOnClickListener{
            val intent4: Intent = Intent(this,OrderListActivity::class.java)
            startActivity(intent4)
        }

        btn_actualizar.setOnClickListener {
            llamarDB(nroTrack)
        }

    }
    private fun Fragmento1() {
        val mFragmento = supportFragmentManager
            .findFragmentById(R.id.mapa) as SupportMapFragment
        mFragmento.getMapAsync(this)
    }

    override fun onMapReady(gMapa: GoogleMap) {
        mapa = gMapa
        Markers()
        estadoPermiso()

    }

    private fun Markers() {
        val coordenadas = LatLng(-9.074637339287742, -78.59365732809074)
        val marker = MarkerOptions().position(coordenadas).title("SABORES DE LA UNS")
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
        mapa.addMarker(marker)
        mapa.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordenadas, 18f),
            4000,
            null
        )
    }

    //Permisos

    //Aceptar permiso
    private fun peticionPermiso() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    //Estado Peticion de Permiso
    private fun estadoPermiso() {
        if (!::mapa.isInitialized) return
        if (peticionPermiso()) {
            mapa.isMyLocationEnabled=true
        } else {
            comprobacionPermiso()
        }
    }

    //Codigo para comprobacion de Permiso
    companion object {
        const val Solicitud_Ubicacion = 0
    }

    private fun comprobacionPermiso() {
        //verificacion de permisos rechazados
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(this, "Se requiere Permiso de UBICACION", Toast.LENGTH_SHORT).show()
        } else {
            //Peticion de permiso por primera vez
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), Solicitud_Ubicacion
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            //Activar Localizacion
            Solicitud_Ubicacion -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mapa.isMyLocationEnabled = true
            } else {
                Toast.makeText(this, "Se requiere Permiso de UBICACION", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }

    //Verificacion de Activacion de Permisos
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::mapa.isInitialized) return
        if(!peticionPermiso()){
            mapa.isMyLocationEnabled = false
            Toast.makeText(this, "Se requiere Permiso de UBICACION", Toast.LENGTH_SHORT).show()
        }
    }


    private fun ruta(lat:Double,log:Double,lat1:Double,log1:Double){
        var linea = PolylineOptions()
            .add(LatLng(lat1, log1))
            .add(LatLng(lat,log))
            //Configracion de Linea
            .color(ContextCompat.getColor(this,R.color.purple_700))
            .width(10f)
        mapa.addPolyline(linea)
    }

    private fun llamarDB(nro: String){
        var latitud: Double=0.0
        var longitud: Double=0.0
        var latitudR: Double=0.0
        var longitudR: Double=0.0
        db.collection("vistaTracking").document(nro).get().addOnSuccessListener {
            latitud=it.get("latitudCl") as Double
            longitud=it.get("longitudCl") as Double
            latitudR=it.get("latitudR") as Double
            longitudR=it.get("longitudR") as Double
            Toast.makeText(this, "UBICACIÓN: ${latitudR}, ${longitudR}", Toast.LENGTH_SHORT).show()
            ruta(latitud,longitud,latitudR,longitudR)
        }

    }
}
