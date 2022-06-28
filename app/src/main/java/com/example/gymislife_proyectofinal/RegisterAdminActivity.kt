package com.example.gymislife_proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.gymislife_proyectofinal.models.AdminModel
import com.example.gymislife_proyectofinal.models.ClienteModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resgister_admin_personal)

        val dbU = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        val etnombre: EditText = findViewById(R.id.txtNombresAdmin)
        val etapellido: EditText = findViewById(R.id.txtApellidosAdmin)
        val etdireccion: EditText = findViewById(R.id.txtDirecAdmin)
        val etmail: EditText = findViewById(R.id.txtmailAdmin)
        val ettelf: EditText = findViewById(R.id.txtTelefonoAdmin)
        val etFechaNac: EditText = findViewById(R.id.txtFechaAdmin)
        val rbgGenero: RadioGroup = findViewById(R.id.rgGeneroAdmin)
        val etDNI: EditText = findViewById(R.id.txtDNIAdmin)
        val etcontra: EditText = findViewById(R.id.txtContraAdmin)
        val btnRegistrar: Button = findViewById(R.id.btnSaveAdmin)

        btnRegistrar.setOnClickListener{
            if(etnombre.text.toString().isEmpty()){
                etnombre.requestFocus()
                etnombre.setError("Ingrese nombres")
            }else if(etapellido.text.toString().isEmpty()){
                etapellido.requestFocus()
                etapellido.setError("Ingrese apellidos")
            }else if(etdireccion.text.toString().isEmpty()){
                etdireccion.requestFocus()
                etdireccion.setError("Ingrese su dirección")
            }else if(etmail.text.toString().isEmpty()){
                etmail.requestFocus()
                etmail.setError("Ingrese su email")
            }else if(ettelf.text.toString().isEmpty()){
                ettelf.requestFocus()
                ettelf.setError("Ingrese su teléfono")
            }else if(etFechaNac.text.toString().isEmpty()){
                etFechaNac.requestFocus()
                etFechaNac.setError("Ingrese su fecha de nacimiento")
            }else if(etDNI.text.toString().length!=8){
                etDNI.requestFocus()
                etDNI.setError("El DNI solo contiene 8 números")
            }else if(etcontra.text.toString().length<8){
                etcontra.requestFocus()
                etcontra.setError("La contraseña debe tener emás de 8 cracateres")
            }else{
                val intSelectedButton: Int= rbgGenero!!.checkedRadioButtonId
                val radioButton: RadioButton =findViewById(intSelectedButton)
                val nombre = etnombre.text.toString()
                val apellido = etapellido.text.toString()
                val direccion = etdireccion.text.toString()
                val mail = etmail.text.toString()
                val telefono = ettelf.text.toString()
                val fechaNac = etFechaNac.text.toString()
                val genero = radioButton.text.toString()
                val dni = etDNI.text.toString()
                val contraseña = etcontra.text.toString()

                dbU.createUserWithEmailAndPassword(dni, contraseña).addOnCompleteListener{
                    val user: FirebaseUser? = dbU.getCurrentUser()
                    val idUser: String = user!!.uid
                    val datosAdmin = AdminModel(
                        nombre,
                        apellido,
                        direccion,
                        mail,
                        telefono,
                        fechaNac,
                        genero,
                        dni,
                        contraseña
                    )
                    db.collection("PersonalAdministrativo").document(idUser).set(datosAdmin)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Registro personal administrativo exitoso", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }.addOnFailureListener {
                            Toast.makeText(this, "Error en el registro", Toast.LENGTH_LONG).show()
                        }
                }
            }
        }
    }
}