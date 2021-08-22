package com.example.restauranteuns

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_interfaz_cliente.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val db= FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_interfaz_cliente)
        var precio=findViewById<View>(R.id.costo) as EditText
        var plato=findViewById<View>(R.id.spinner) as Spinner
        val Chaufa=15
        val saltado=25
        val Ceviche=22
        val Cliente="fernando"
        val spinner=findViewById<Spinner>(R.id.spinner)
        var listo= listOf("pollo a la brasa","chaufa","lomo saltado","ceviche")
        var comida=0
        val adaptador=ArrayAdapter(this,android.R.layout.simple_spinner_item,listo)
        spinner.adapter=adaptador
        var seleccion=spinner.selectedItem.toString()
        continuar.setOnClickListener{
            if(seleccion.equals("pollo a la brasa")){
                comida=20
            }else{ if(seleccion.equals("chaufa")){
                comida=15
            }else{if (seleccion.equals("lomo saltado")){
                comida=25
            }else{
                comida=22
            }

            }
            }
            var resultado= comida.toInt()+costo.text.toString().toInt()

                db.collection("cliente").document(seleccion).set(
                    hashMapOf(
                        "cliente" to Cliente,
                        "platos" to seleccion,
                        "cantidad" to costo.text.toString(),
                        "coste" to comida,
                        "total" to resultado


                    )


                )





            startActivity(Intent(this,tracking::class.java))
        }
        fun onContextItemSelected(item: MenuItem): Boolean {
            return super.onContextItemSelected(item)
        }
    }

}