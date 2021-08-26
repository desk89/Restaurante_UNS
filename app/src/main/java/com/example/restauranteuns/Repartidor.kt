package com.example.restauranteuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.list_item.*

class Repartidor : AppCompatActivity() {


    private  lateinit var recyclerView: RecyclerView
    private  lateinit var pedArrayList: ArrayList<Pedido>
    private  lateinit var myAdapter: MyAdapter
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repartidor)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        pedArrayList = arrayListOf()

        myAdapter = MyAdapter(pedArrayList)

        recyclerView.adapter = myAdapter


        EvenChangeListener()

        boton_mapaRepartidor.setOnClickListener {
            startActivity(Intent(this,MapRepartidorActivity::class.java))
        }
    }

    private fun EvenChangeListener() {
        db.collection("pedidos").
        addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null){
                    Log.e("Firestore Error",error.message.toString())
                    return
                }

                for (dc : DocumentChange in value?.documentChanges!!){

                    if (dc.type == DocumentChange.Type.ADDED){
                        pedArrayList.add(dc.document.toObject(Pedido::class.java))
                    }
                }

                myAdapter.notifyDataSetChanged()
            }

        })
    }


}