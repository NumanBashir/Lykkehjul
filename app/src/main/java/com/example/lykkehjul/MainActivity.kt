package com.example.lykkehjul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
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
            //antalLiv = antalLiv - 1

        }



    }
}