package com.example.restauranteuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tracking.*

class TrackingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking)


        btn_verificarcodigo.setOnClickListener{
            startActivity(Intent(this,MapActivity::class.java).putExtra
                ("tracking",et_codigo.text.toString()))
        }
    }
}