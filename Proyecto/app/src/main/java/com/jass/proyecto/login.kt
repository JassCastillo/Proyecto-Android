package com.jass.proyecto

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlin.properties.Delegates

class login  : AppCompatActivity() {

    private val TAG = "login"
    //global variables
    private var email by Delegates.notNull<String>()
    private var password by Delegates.notNull<String>()
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var mProgressBar: ProgressDialog

    //Creamos nuestra variable de autenticación firebase
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login  )
        initialice()
    }

    private fun initialice() {
        emailEditText = findViewById(R.id.editEmail)!!
        passwordEditText = findViewById(R.id.editPass)!!
        mProgressBar = ProgressDialog(this)!!
        mAuth = FirebaseAuth.getInstance()!!
    }
    private fun loginUser()
    {
        //Obtenemos usuario y contraseña
        email = emailEditText.text.toString()
        password = passwordEditText.text.toString()
        //Verificamos que los campos no este vacios
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            //Mostramos el progressdialog
            mProgressBar.setMessage("Registering User...")
            mProgressBar.show()

            //Iniciamos sesión con el método signIn y enviamos usuario y contraseña
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    //Verificamos que la tarea se ejecutó correctamente
                        task ->
                    if (task.isSuccessful) {
                        // Si se inició correctamente la sesión vamos a la vista Home de la aplicación
                        goHome() // Creamos nuestro método en la parte de abajo
                    } else {
                        // sino le avisamos el usuairo que orcurrio un problema
                        Toast.makeText(this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goHome() {
        mProgressBar.hide()
        //Nos vamos a Home
        val intent = Intent(this, Principal::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun login(view: View) {
        loginUser()
    }
    fun register(view: View) {}
    fun forgotPassword(view: View) {}
}