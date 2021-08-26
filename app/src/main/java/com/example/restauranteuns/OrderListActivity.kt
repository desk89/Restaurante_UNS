package com.example.restauranteuns
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        val nroIntent: Intent=intent
        var numero = nroIntent.getStringExtra("nro")
        tv_pedidos.text=numero

        btn_ubicacion.setOnClickListener{
            startActivity(Intent(this,MapActivity::class.java).putExtra(
                "tracking",numero
            ))
        }
    }
}