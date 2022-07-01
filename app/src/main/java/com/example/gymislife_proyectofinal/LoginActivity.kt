package com.example.gymislife_proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etDNI: EditText = findViewById(R.id.txtDniLogin)
        val etpassword: EditText = findViewById(R.id.txtpassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnRegsitroCliente: Button = findViewById(R.id.btnRegistroCliente)
        val btnRegistroAdmin: Button = findViewById(R.id.btnRegistroPersonal)
        val dbU = FirebaseAuth.getInstance()
        val dbFirestore = FirebaseFirestore.getInstance()

        btnRegsitroCliente.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        btnRegistroAdmin.setOnClickListener {
            val intent = Intent(this,RegisterAdminActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val dni = etDNI.text.toString()
            val password =etpassword.text.toString()
            var isUser: Boolean=false


            var document = dbFirestore.collection("Clientes")
                .whereEqualTo("DNI",dni).whereEqualTo("Contraseña",password)
                .get()
                .addOnSuccessListener { documents->
                    if(documents.isEmpty){
                        Toast.makeText(this,"Usuario NO existe", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"ACCESO CONCEDIDO", Toast.LENGTH_LONG).show()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }


                }


            Log.d("Document", document.toString())
            /*dbFirestore.collection("Clientes")
                .addSnapshotListener{snapshots, e->
                    if(e!=null){
                        Snackbar.make(findViewById(androidx.appcompat.R.id.content),"Error",Snackbar.LENGTH_LONG).show()
                        return@addSnapshotListener
                    }

                    for (dc in snapshots!!.documentChanges){
                        dc.document.data["DNI"].toString() = dni
                    }



                }*/
            /*dbU.signInWithEmailAndPassword(dni, password).addOnCompleteListener(this) { task->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "ACCESO CONCEDIDO",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this,
                        "EL DNI Y/O CLAVE NO EXISTE EN EL SISTEMA",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }*/
        }
    }
}