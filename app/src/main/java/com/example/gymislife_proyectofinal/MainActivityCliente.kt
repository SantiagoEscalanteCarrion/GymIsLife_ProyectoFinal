package com.example.gymislife_proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivityCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cliente)

        val btnInscripcion: Button = findViewById(R.id.btnIscripcion)
        val btnMembresia: Button = findViewById(R.id.btnMembresia)
        val btnPerfilCliente: Button = findViewById(R.id.btnPerfilCliente)

        btnInscripcion.setOnClickListener{
            val intent = Intent(this,ListadoActividadCliente::class.java)
            startActivity(intent)
        }

    }
}