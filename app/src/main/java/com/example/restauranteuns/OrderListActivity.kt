package com.example.restauranteuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        btn_ubicacion.setOnClickListener{
            val intent3: Intent = Intent(this,MapActivity::class.java)
            startActivity(intent3)
        }
    }
}