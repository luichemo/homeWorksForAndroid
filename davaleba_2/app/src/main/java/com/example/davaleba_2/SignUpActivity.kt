package com.example.davaleba_2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpEmailEditText : EditText
    private lateinit var signUpPasswordEditText : EditText
    private lateinit var signUpButton : Button
    private lateinit var alreadyRegisteredButton : TextView

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()
        listeners()
    }

    private fun listeners() {

        signUpButton.setOnClickListener {
            val email = signUpEmailEditText.text.toString()
            val password = signUpPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty() ||
                password.length < 6 || password.contains(" ")){
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    goToLogin()
                } else {
                    Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }


        }

        alreadyRegisteredButton.setOnClickListener {
            goToLogin()
        }

    }

    private fun goToLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    private fun init(){
        signUpEmailEditText = findViewById(R.id.signUpEmailEditText)
        signUpPasswordEditText = findViewById(R.id.signUpPasswordEditText)
        signUpButton = findViewById(R.id.signUpButton)
        alreadyRegisteredButton = findViewById(R.id.alreadyRegisteredButton)
    }

}