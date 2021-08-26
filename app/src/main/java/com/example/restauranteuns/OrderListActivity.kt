package com.example.restauranteuns
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.activity_order_list.view.*

class OrderListActivity : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        val nroIntent: Intent=intent
        var numero = nroIntent.getStringExtra("nro")
        textoNro.text=numero.toString()
        var nro=textoNro.text.toString()
        llamarBD(nro)

        btn_ubicacion.setOnClickListener{
            startActivity(Intent(this,MapActivity::class.java).putExtra(
                "tracking",numero            ))
        }
        
    }
    private fun llamarBD(nro: String){
        db.collection("pedidos").document(nro).get().addOnSuccessListener {
            textoPlato.text=it.get("plato") as String
            textoCantidad.text=it.get("cantidad") as String
            textoTotal.text=it.get("total") as String
        }
    }

}