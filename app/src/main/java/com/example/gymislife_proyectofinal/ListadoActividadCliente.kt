package com.example.gymislife_proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymislife_proyectofinal.adapter.ActivityClienteAdapter
import com.example.gymislife_proyectofinal.models.ActividadModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class ListadoActividadCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_actividad_cliente)

        val db = FirebaseFirestore.getInstance()

        val lstActClient: ArrayList<ActividadModel> = ArrayList()
        val rvActClient: RecyclerView = findViewById(R.id.rvActividadCliente)
        val btnBackUno: Button = findViewById(R.id.btnBackUno)
        btnBackUno.setOnClickListener{
            val intent = Intent(this,MainActivityCliente::class.java)
            startActivity(intent)
        }

        db.collection("Actividad")
            .addSnapshotListener{snapshots, e->
                if(e!=null){
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


    }


}