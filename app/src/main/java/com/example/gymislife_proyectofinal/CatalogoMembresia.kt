package com.example.gymislife_proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymislife_proyectofinal.adapter.ActivityClienteAdapter
import com.example.gymislife_proyectofinal.adapter.MembresiaAdapter
import com.example.gymislife_proyectofinal.models.ActividadModel
import com.example.gymislife_proyectofinal.models.MembresiaModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class CatalogoMembresia : AppCompatActivity() {
    override fun onCreate(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_membresia)

        val view: View = inflater.inflate(R.layout.activity_catalogo_membresia, container, false)
        val db = FirebaseFirestore.getInstance()

        val lstMembresia: ArrayList<MembresiaModel> = ArrayList()
        val rvMembresia: RecyclerView = view.findViewById(R.id.rvMembresia)
        val btnPagar: Button = view.findViewById(R.id.btnPagar)
        btnPagar.setOnClickListener{
            Snackbar.make(btnPagar, "Continuar con la transacción", Snackbar.LENGTH_LONG)
                .setAction("Cancel"){}
                .show()
        }

        db.collection("Membresía")
            .addSnapshotListener{snapshots, e->
                if(e!=null){
                    Snackbar.make(view, "Error al cargar MEMBRESÍAS", Snackbar.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                for (dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED,
                        DocumentChange.Type.MODIFIED,
                        DocumentChange.Type.REMOVED -> {
                            lstMembresia.add(
                                MembresiaModel(dc.document.data["Nombre"].toString(),
                                    dc.document.data["Duración"].toString(),
                                    dc.document.data["Precio"].toString())
                            )
                        }
                    }
                }

                rvMembresia.adapter = MembresiaAdapter(lstMembresia)
                rvMembresia.layoutManager = LinearLayoutManager(this)
            }

        return view
    }
}