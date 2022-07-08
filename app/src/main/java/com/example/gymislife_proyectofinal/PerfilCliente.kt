package com.example.gymislife_proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class PerfilCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_cliente)

        val etNombCliente: EditText = findViewById(R.id.etNombCliente)
        val etApellCliente: EditText = findViewById(R.id.etApellCliente)
        val etDNICliente: EditText = findViewById(R.id.etDNICliente)
        val etDirecCliente: EditText = findViewById(R.id.etDirecCliente)
        val etEmailCliente: EditText = findViewById(R.id.etEmailCliente)
        val etGenCliente: EditText = findViewById(R.id.etGenCliente)
        val etTelfCliente: EditText = findViewById(R.id.etTelfCliente)
        val etContraCliente: EditText = findViewById(R.id.etContraCliente)
        val btnEditarPerfCliente: Button = findViewById(R.id.btnEditarPerfCliente)
        val btnBack: Button = findViewById(R.id.btnBack3)
        var editing:Boolean = false

        btnBack.setOnClickListener{
            val intent = Intent(this,MainActivityCliente::class.java)
            startActivity(intent)
        }

        val db = FirebaseFirestore.getInstance()

        db.collection("Clientes")
            .addSnapshotListener{snapshot, e->
                if(e!=null){
                    Log.w("Firebase Warning", "Error", e)
                    return@addSnapshotListener
                }


            }

    }
}