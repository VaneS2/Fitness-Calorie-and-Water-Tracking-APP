package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)




        val button=findViewById<Button>(R.id.signUp)

        button.setOnClickListener {

            val email: EditText = findViewById(R.id.emailUsername)
            val password: EditText = findViewById(R.id.passwordd)
            val passwordRe: EditText = findViewById(R.id.passwordRe)

            val emailValue: String = email.text.toString()
            val passwordValue: String = password.text.toString()

            if(emailValue.isNullOrEmpty() || passwordValue.isNullOrEmpty()) {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }else {
                register(emailValue, passwordValue)
            }
        }

    }

    private fun register(emailValue: String, passwordValue: String) {
        mAuth.createUserWithEmailAndPassword(emailValue, passwordValue)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this, "Registration success.",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent: Intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)


                } else {
                    Toast.makeText(
                        this, "Registration failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


}