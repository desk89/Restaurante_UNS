package com.example.restauranteuns

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Boton registro
        botonRegistro.setOnClickListener {
            startActivity(Intent(this,RegistrarseActivity::class.java))
        }
        //Boton logear
        botonLogin.setOnClickListener{
            //Busca el email y si existe -> me deja entrar al principal
            db.collection("usuarios").document(editEmailLog.text.toString()).get().addOnSuccessListener {
                startActivity(Intent(this,PrincipalCliente::class.java).putExtra(
                    "emailLogin",editEmailLog.text.toString()
                ))
            }
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
        builder.setTitle("Hasta aqui")
        builder.setMessage(texto)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()
    }

}