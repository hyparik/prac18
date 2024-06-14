package com.example.prac18

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewFilms : AppCompatActivity() {
    lateinit var filmsTextView: TextView
    var pref: SharedPreferences? = null
    val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_films)
        filmsTextView = findViewById(R.id.filmsTextView)
        pref = getSharedPreferences("FilmApp", MODE_PRIVATE)

        val filmsJson = pref?.getString("films", "[]")
        val filmsType = object : TypeToken<List<Film>>() {}.type
        val films: List<Film> = gson.fromJson(filmsJson, filmsType) ?: emptyList()

        val booksText = films.joinToString(separator = "\n") { "Название: ${it.title}, Автор: ${it.year}" }
        filmsTextView.text = booksText
    }
}