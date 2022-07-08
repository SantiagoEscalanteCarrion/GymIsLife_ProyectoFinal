package com.example.gymislife_proyectofinal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymislife_proyectofinal.R
import com.example.gymislife_proyectofinal.models.ActividadModel

class ActivityClienteAdapter (private var lstActCliente: List<ActividadModel>)
    : RecyclerView.Adapter<ActivityClienteAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvNombAct: TextView = itemView.findViewById(R.id.tvNombAct)
        val tvInstAct: TextView = itemView.findViewById(R.id.tvInstAct)
        val tvHorAct: TextView = itemView.findViewById(R.id.tvHorAct)
        val tvDurAct: TextView = itemView.findViewById(R.id.tvDurAct)
        val tvFechAct: TextView = itemView.findViewById(R.id.tvFechaAct)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_actividad_cliente, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemActClient = lstActCliente[position]
        holder.tvNombAct.text = itemActClient.nombre
        holder.tvInstAct.text = itemActClient.instructor
        holder.tvHorAct.text = itemActClient.hora
        holder.tvDurAct.text = itemActClient.duracion
        holder.tvFechAct.text = itemActClient.fecha
    }

    override fun getItemCount(): Int {
        return lstActCliente.size
    }
}