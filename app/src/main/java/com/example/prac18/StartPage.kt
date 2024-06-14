package com.example.prac18

import android.R.attr.value
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

lateinit var loginInput: EditText
lateinit var passwordInput: EditText

class StartPage : AppCompatActivity() {
    var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start_page)
        loginInput = findViewById(R.id.signIn_login)
        passwordInput = findViewById(R.id.signIn_password)

        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)

    }

    fun onClickSignIn(view: View) {
        var login = loginInput.getText().toString()
        var password = passwordInput.getText().toString()

        var trueLogin = pref?.getString("Login", "").toString()
        var truePassword = pref?.getString("Password", "").toString()

        if(login.length == 0){
            Toast.makeText(this, "Input Login is empty", Toast.LENGTH_SHORT).show()
        }
        else if(password.length == 0){
            Toast.makeText(this, "Input Password is empty", Toast.LENGTH_SHORT).show()
        }
        else if(login == trueLogin && password == truePassword){
            val myIntent: Intent = Intent(
                this@StartPage,
                HomePage::class.java
            )
            this@StartPage.startActivity(myIntent)
        }
        else{
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickSignUp(view: View) {
        val myIntent: Intent = Intent(
            this@StartPage,
            SignUp::class.java
        )
        this@StartPage.startActivity(myIntent)
    }
}