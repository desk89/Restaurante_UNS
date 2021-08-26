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
            if(editEmailLog.text.toString()=="diego@gmail.com"){
                db.collection("usuarios").document(editEmailLog.text.toString()).get().addOnSuccessListener {
                    val contraseña = it.get("contraseña") as String
                    if (contraseña==editPasswordLog.text.toString()){
                        startActivity(Intent(this,Repartidor::class.java).putExtra(
                            "emailLogin",editEmailLog.text.toString()
                        ))
                    }else{
                      mensaje("Contraseña Incorrecta")
                    }
            }
        }else{
            if (editEmailLog.text.toString().isNotEmpty()){
                db.collection("usuarios").document(editEmailLog.text.toString()).get().addOnSuccessListener {
                    val contraseña = it.get("contraseña") as String
                    if (contraseña==editPasswordLog.text.toString()){
                        startActivity(Intent(this,PrincipalCliente::class.java).putExtra(
                            "emailLogin",editEmailLog.text.toString()
                        ))
                    }else{
                        mensaje("Contraseña Incorrecta")
                    }
                }
            }else{
                mensaje("Ingresa tu correo")
            }
        }

    }
    }

    private fun mensaje(entrada:String){
        val builder = AlertDialog.Builder( this)
        var texto = entrada
        builder.setTitle("Algo ha ocurrido")
        builder.setMessage(texto)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()
    }

}
