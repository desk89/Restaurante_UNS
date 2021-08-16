package com.example.restauranteuns

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        botonRegistrarse.setOnClickListener {

        }
    }

    private fun alerta(){
        val builder = AlertDialog.Builder( this)
        builder.setTitle("Error")
        builder.setMessage("Hubo un error")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()
    }

    private fun mensaje(entrada:String){
        val builder = AlertDialog.Builder( this)
        var email = entrada
        //val builder= AlertDialog.Builder(context:this)
        builder.setTitle("Hasta aqui")
        builder.setMessage(email)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()
    }

}