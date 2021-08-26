package com.example.restauranteuns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val pediList: ArrayList<Pedido>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val pedido : Pedido = pediList[position]
        holder.cantidad.text = pedido.cantidad
        holder.usuario.text = pedido.usuario
        holder.plato.text = pedido.plato
        holder.total.text = pedido.total.toString()
        holder.nroTracking.text = pedido.nroTracking.toString()


    }

    override fun getItemCount(): Int {
        return pediList.size
    }


    public class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val usuario :TextView = itemView.findViewById(R.id.tvusuario)
        val plato : TextView = itemView.findViewById(R.id.tvplato)
        val total : TextView = itemView.findViewById(R.id.tvtotal)
        val cantidad : TextView = itemView.findViewById(R.id.tvcantidad)
        val nroTracking : TextView = itemView.findViewById(R.id.tvtracking)
        val boton_mapa: ImageView = itemView.findViewById(R.id.boton_mapaRepartidor)

    }
}