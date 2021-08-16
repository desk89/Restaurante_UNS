package com.example.restauranteuns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner=findViewById<Spinner>(R.id.spinner)
        var listo= listOf("pollo a la brasa","chaufa","lomo saltado","ceviche")
        val adaptador=ArrayAdapter(this,android.R.layout.simple_spinner_item,listo)
        spinner.adapter=adaptador
    }
}