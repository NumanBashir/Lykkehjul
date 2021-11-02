package com.example.lykkehjul

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isInvisible
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // List words
        val words = listOf("kotlin", "java", "python", "swift")
        val randomElement = words.random()
        println(randomElement)
        Log.i("random element from array", randomElement)

        // Convert string into CharArray
        val chars: CharArray = randomElement.toCharArray()
        println(chars.contentToString())

        val startSpilKnap: Button = findViewById(R.id.startSpilKnap)
        val ordTilView: TextView = findViewById(R.id.ordTilView)
        var antalLiv: TextView = findViewById(R.id.antalLiv)

        startSpilKnap.setOnClickListener {
            ordTilView.setText(randomElement)
            startSpilKnap.setText("Drej hjulet!")
            //antalLiv = antalLiv - 1


            /* Make the word invisible
            ordTilView.visibility = View.GONE */



        }



    }
}