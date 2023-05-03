package com.example.davaleba_2



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var forgotPasswordEmailEditText : EditText
    private lateinit var sendButton : Button

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        init()
        listeners()
    }

    private fun listeners() {
        sendButton.setOnClickListener {
            val email = forgotPasswordEmailEditText.text.toString()
            if (email.isEmpty()){
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email).addOnCompleteListener{
                if (it.isSuccessful){
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun init(){
        forgotPasswordEmailEditText = findViewById(R.id.forgotPasswordEmailEditText)
        sendButton = findViewById(R.id.sendButton)
    }
}