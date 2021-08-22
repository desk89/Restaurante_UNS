package com.example.restauranteuns

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registrarse.*

class RegistrarseActivity : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        //Boton Registrar persona
        botonRegistrarse.setOnClickListener {
            if (editNombreRegist.text.isNotEmpty()&&editEmailRegist.text.isNotEmpty()
                &&editContraseñaRegist.text.isNotEmpty()){
                var tipo=1
                var latitud=1
                var longitud=1
                //Creo mi usuario
                db.collection("usuarios").document(editEmailRegist.text.toString()).set(
                    hashMapOf("nombre" to editNombreRegist.text.toString(),
                        "email" to editEmailRegist.text.toString(),
                        "contraseña" to editContraseñaRegist.text.toString(),
                        "tipo" to tipo.toString(),
                        "direccion" to editDireccionRegist.text.toString(),
                        "latitud" to latitud.toString(),
                        "longitud" to longitud.toString())
                )
                //Devolver al login
                val intentoLogin= Intent(this,MainActivity::class.java)
                startActivity(intentoLogin)
            }else{
                mensaje("Te falto llenar un campo")
            }
        }
        //Boton voler al login
        botonVolver.setOnClickListener {
            val intentoLogin= Intent(this,MainActivity::class.java)
            startActivity(intentoLogin)
        }
    }

    private fun mensaje(entrada:String){
        val builder = AlertDialog.Builder( this)
        var texto = entrada
        //val builder= AlertDialog.Builder(context:this)
        builder.setTitle("Hasta aqui")
        builder.setMessage(texto)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }

}