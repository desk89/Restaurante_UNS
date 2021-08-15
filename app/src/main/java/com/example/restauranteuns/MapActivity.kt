package com.example.restauranteuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        btn_vlista.setOnClickListener{
            val intent4: Intent = Intent(this,OrderListActivity::class.java)
            startActivity(intent4)
        }
    }
}