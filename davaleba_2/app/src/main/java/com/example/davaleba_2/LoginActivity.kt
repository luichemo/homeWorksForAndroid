package com.example.davaleba_2



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var loginEmailEditText: EditText
    private lateinit var loginPasswordEditText : EditText
    private lateinit var loginButton : Button
    private lateinit var notRegisteredButton : TextView
    private lateinit var forgotPasswordButton : TextView

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        listeners()
    }

    private fun listeners() {
        loginButton.setOnClickListener {
            val email = loginEmailEditText.text.toString()
            val password = loginPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    startActivity(Intent(this,ProfileActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

        }

        notRegisteredButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        forgotPasswordButton.setOnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
            finish()
        }
    }


    private fun init(){
        loginEmailEditText = findViewById(R.id.loginEmailEditText)
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        notRegisteredButton = findViewById(R.id.notRegisteredButton)
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)
    }

}