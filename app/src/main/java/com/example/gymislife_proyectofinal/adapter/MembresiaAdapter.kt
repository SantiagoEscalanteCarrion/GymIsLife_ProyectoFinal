package com.example.gymislife_proyectofinal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymislife_proyectofinal.R
import com.example.gymislife_proyectofinal.models.MembresiaModel

class MembresiaAdapter (private var lstMembresia: List<MembresiaModel>)
    :RecyclerView.Adapter<MembresiaAdapter.ViewHolder>(){
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val txtNombMemb: TextView = itemView.findViewById(R.id.tvNombMemb)
            val txtDurMemb: TextView = itemView.findViewById(R.id.tvDurMemb)
            val txtPrecioMemb: TextView = itemView.findViewById(R.id.tvPrecioMemb)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_membresia, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemMemb = lstMembresia[position]
        holder.txtNombMemb.text = itemMemb.nombre
        holder.txtDurMemb.text = itemMemb.duracion
        holder.txtPrecioMemb.text = itemMemb.precio
    }

    override fun getItemCount(): Int {
        return lstMembresia.size
    }


}