package com.example.restauranteuns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class Repartidor : AppCompatActivity() {


    private  lateinit var recyclerView: RecyclerView
    private  lateinit var pedArrayList: ArrayList<Pedido>
    private  lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repartidor)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        pedArrayList = arrayListOf()

        myAdapter = MyAdapter(pedArrayList)

        recyclerView.adapter = myAdapter


        EvenChangeListener()
    }

    private fun EvenChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("Pedidos").
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