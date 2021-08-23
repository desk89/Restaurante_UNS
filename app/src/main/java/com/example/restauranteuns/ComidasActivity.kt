package com.example.restauranteuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_comida.*

class ComidasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val db= FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_comida)

        val Cliente="fernando"
        val spinner=findViewById<Spinner>(R.id.spinner)
        val listo= arrayOf("pollo a la brasa","chaufa","lomo saltado","ceviche")
        var comida=0
        val adaptador=ArrayAdapter(this,android.R.layout.simple_spinner_item,listo)
        spinner.adapter=adaptador
        val seleccion=spinner.selectedItem.toString()
        print(seleccion)


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
            var resultado= comida.toInt()*costo.text.toString().toInt()

                db.collection("another").document(seleccion).set(
                    hashMapOf(
                        "cliente" to Cliente,
                        "platos" to seleccion,
                        "cantidad" to costo.text.toString(),
                        "coste" to comida,
                        "total" to resultado

                    )


                )

            startActivity(Intent(this,TrackingActivity::class.java))
        }
        fun onContextItemSelected(item: MenuItem): Boolean {
            return super.onContextItemSelected(item)
        }
    }

}