package com.example.restauranteuns

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registrarse.*

class RegistrarseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        //Boton Registrar persona
        botonRegistrarse.setOnClickListener {
            if (editNombreRegist.text.isNotEmpty()&&editEmailRegist.text.isNotEmpty()
                &&editContraseñaRegist.text.isNotEmpty()){
                mensaje("tus campos estan llenos")
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