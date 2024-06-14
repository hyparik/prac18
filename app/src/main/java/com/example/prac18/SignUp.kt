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

lateinit var RegLogin: EditText
lateinit var RegPassword: EditText
lateinit var RegConfPassword: EditText

class SignUp : AppCompatActivity() {
    var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        RegLogin = findViewById(R.id.reg_login)
        RegPassword = findViewById(R.id.reg_password)
        RegConfPassword = findViewById(R.id.reg_conf_password)

        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
    }

    fun onClickRegSignUp(view: View) {
        var login = RegLogin.getText().toString()
        var password = RegPassword.getText().toString()
        var conf_password = RegConfPassword.getText().toString()

        if(login.length == 0){
            Toast.makeText(this, "Input Login is empty", Toast.LENGTH_SHORT).show()
        }
        else if(password.length == 0){
            Toast.makeText(this, "Input Password is empty", Toast.LENGTH_SHORT).show()
        }
        else if(conf_password.length == 0){
            Toast.makeText(this, "Input Confirm password is empty", Toast.LENGTH_SHORT).show()
        }
        else if(password != conf_password){
            Toast.makeText(this, "The Password and Confirm password inputs do not match", Toast.LENGTH_SHORT).show()
        }
        else{
            saveUser(login, password)
            Toast.makeText(this, "You have successfully registered", Toast.LENGTH_SHORT).show()
            RegLogin.text = null
            RegPassword.text = null
            RegConfPassword.text = null
        }
    }

    fun saveUser(login: String, password: String){
        val editor = pref?.edit()
        editor?.putString("Login", login)
        editor?.putString("Password", password)
        editor?.apply()
    }

    fun onClickBack(view: View) {
        this.finish()
    }
}