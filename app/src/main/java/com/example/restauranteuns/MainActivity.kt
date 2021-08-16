package com.example.restauranteuns

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //val FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Boton registro
        botonRegistro.setOnClickListener {
            val intentoRegistro = Intent(this,RegistrarseActivity::class.java)
            startActivity(intentoRegistro)
        }
        //Boton logear
        botonLogin.setOnClickListener{
            val intentPrincipal = Intent(this,PrincipalCliente::class.java)
            startActivity(intentPrincipal)
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
        var texto = entrada
        //val builder= AlertDialog.Builder(context:this)
        builder.setTitle("Hasta aqui")
        builder.setMessage(texto)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()
    }

}