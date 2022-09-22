package com.example.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)


        Handler().postDelayed({
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        },2000)
    }



}