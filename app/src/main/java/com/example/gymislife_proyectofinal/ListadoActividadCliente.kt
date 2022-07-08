package com.example.gymislife_proyectofinal

import android.graphics.drawable.DrawableContainer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatViewInflater
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymislife_proyectofinal.adapter.ActivityClienteAdapter
import com.example.gymislife_proyectofinal.models.ActividadModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ListadoActividadCliente : AppCompatActivity() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_actividad_cliente)
        val view: View = inflater.inflate(R.layout.activity_listado_actividad_cliente, container, false)
        val db = FirebaseFirestore.getInstance()

        val lstActClient: ArrayList<ActividadModel> = ArrayList()
        val rvActClient: RecyclerView = view.findViewById(R.id.rvActividadCliente)
        val btnInscribirse: Button = view.findViewById(R.id.btnInscribirse)
        btnInscribirse.setOnClickListener{
            Snackbar.make(btnInscribirse, "Incrito satisfactoriamente", Snackbar.LENGTH_LONG).show()
        }

        db.collection("Actividad")
            .addSnapshotListener{snapshots, e->
                if(e!=null){
                    Snackbar.make(view, "Error al cargar ACTIVIDADES", Snackbar.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                for (dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED,
                        DocumentChange.Type.MODIFIED,
                        DocumentChange.Type.REMOVED -> {
                            lstActClient.add(
                                ActividadModel(dc.document.data["Nombre"].toString(),
                                               dc.document.data["Instructor"].toString(),
                                               dc.document.data["Hora"].toString(),
                                               dc.document.data["Duracion"].toString(),
                                               dc.document.data["Fecha"].toString())
                            )
                        }
                    }
                }

                rvActClient.adapter = ActivityClienteAdapter(lstActClient)
                rvActClient.layoutManager = LinearLayoutManager(this)
            }

        return view
    }


}