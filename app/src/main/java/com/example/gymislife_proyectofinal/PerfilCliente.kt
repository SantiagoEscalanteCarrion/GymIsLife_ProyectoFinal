package com.example.gymislife_proyectofinal

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class PerfilCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_cliente)

        val dni =intent.getStringExtra("DNI").toString()

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

        etNombCliente.isEnabled = false
        etApellCliente.isEnabled = false
        etDNICliente.isEnabled = false
        etDirecCliente.isEnabled = false
        etEmailCliente.isEnabled = false
        etGenCliente.isEnabled = false
        etTelfCliente.isEnabled = false
        etContraCliente.isEnabled = false


        var edit:Boolean = false
        btnBack.setOnClickListener{
            val intent = Intent(this,MainActivityCliente::class.java)
            startActivity(intent)
        }

        val db = FirebaseFirestore.getInstance()

        db.collection("Clientes")
            .addSnapshotListener{snapshot, e->
                if(e!=null){
                    Log.w("FirebaseWarning","ERROR",e)
                    return@addSnapshotListener
                }
                for(dc in snapshot!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED ->{
                            etNombCliente.setText(dc.document.data["Nombre"].toString())
                            etApellCliente.setText(dc.document.data["Apellido"].toString())
                            etDNICliente.setText(dc.document.data["DNI"].toString())
                            etDirecCliente.setText(dc.document.data["Direccion"].toString())
                            etEmailCliente.setText(dc.document.data["Email"].toString())
                            etGenCliente.setText(dc.document.data["Genero"].toString())
                            etTelfCliente.setText(dc.document.data["Telefono"].toString())
                            etContraCliente.setText(dc.document.data["Contraseña"].toString())
                        }DocumentChange.Type.MODIFIED ->{
                            etNombCliente.setText(dc.document.data["Nombre"].toString())
                            etApellCliente.setText(dc.document.data["Apellido"].toString())
                            etDNICliente.setText(dc.document.data["DNI"].toString())
                            etDirecCliente.setText(dc.document.data["Direccion"].toString())
                            etEmailCliente.setText(dc.document.data["Email"].toString())
                            etGenCliente.setText(dc.document.data["Genero"].toString())
                            etTelfCliente.setText(dc.document.data["Telefono"].toString())
                            etContraCliente.setText(dc.document.data["Contraseña"].toString())
                        }DocumentChange.Type.REMOVED ->{
                            Log.w("Firebase Warning","REMOVED")
                        }
                    }
                }
            }

        btnEditarPerfCliente.setOnClickListener {
            if(edit==false){
                etDirecCliente.isEnabled = true
                etTelfCliente.isEnabled = true
                etEmailCliente.isEnabled = true
                btnEditarPerfCliente.setText("Guardar")
                edit=true
            }else{
                etDirecCliente.isEnabled = false
                etTelfCliente.isEnabled = false
                etEmailCliente.isEnabled = false
                btnEditarPerfCliente.setText("Editar")
                edit=false


            }
        }
    }
}