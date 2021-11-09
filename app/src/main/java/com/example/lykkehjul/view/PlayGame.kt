package com.example.lykkehjul.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.lykkehjul.GameEvents
import com.example.lykkehjul.R
import com.example.lykkehjul.data.Datasource
import java.lang.StringBuilder

class PlayGame : AppCompatActivity() {

    private val gameManager = GameEvents()

    var lives = 5
    var visHemmeligOrd = ""
    var rigtigeGæt = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)

        /*val gameState = gameManager.startSpil()
        gameManager.hideWord(hemmeligtOrd)*/

        val ord: TextView = findViewById(R.id.ord)
        var secretOrd = ""

        // Load words
        val myDataset = Datasource().loadWords()

        // Pick a random element from dataset
        var hemmeligtOrd = myDataset.random().toString()

        // Set each letter of word to "_"
        val sb = StringBuilder()
        hemmeligtOrd.forEach {
            sb.append("_")
        }
        secretOrd = sb.toString()

        // Set textView to the random word
        ord.setText(secretOrd)

        val drejHjulKnap: Button = findViewById(R.id.drejHjul)
        val wheelOutcomeDisplay: TextView = findViewById(R.id.wheelOutcome)


        drejHjulKnap.setOnClickListener {
            val wheelOutcomes = Datasource().loadWheel()
            val randomOutcome = wheelOutcomes.random().toString()

            wheelOutcomeDisplay.setText(randomOutcome)

        }


    }



}