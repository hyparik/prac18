package com.example.prac18

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Film(val title: String, val year: Int)



class HomePage : AppCompatActivity() {
    lateinit var titleInput: EditText
    lateinit var yearInput: EditText
    var pref: SharedPreferences? = null
    val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        titleInput = findViewById(R.id.title)
        yearInput = findViewById(R.id.year)
        pref = getSharedPreferences("FilmApp", MODE_PRIVATE)
    }

    fun onClickAddFilm(view: View) {
        var title = titleInput.getText().toString()
        var year = yearInput.getText().toString()
        if(title.length == 0){
            Toast.makeText(this, "Input Title is empty", Toast.LENGTH_SHORT).show()
        }
        else if(year.length == 0){
            Toast.makeText(this, "Input Year is empty", Toast.LENGTH_SHORT).show()
        }
        else if(Integer.parseInt(year) > 2024){
            Toast.makeText(this, "The year cannot be more than 2024", Toast.LENGTH_SHORT).show()
        }
        else{
            val filmsJson = pref?.getString("films", "[]")
            val filmsType = object : TypeToken<MutableList<Film>>() {}.type
            val films: MutableList<Film> = gson.fromJson(filmsJson, filmsType) ?: mutableListOf()

            films.add(Film(title, Integer.parseInt(year)))
            val newFilmsJson = gson.toJson(films)
            val editor = pref?.edit()
            editor?.putString("films", newFilmsJson)
            editor?.apply()
        }
    }
    fun onClickShow(view: View) {
        val myIntent: Intent = Intent(
        this@HomePage,
        ViewFilms::class.java
    )
        this@HomePage.startActivity(myIntent)}
}