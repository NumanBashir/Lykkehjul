package com.example.lykkehjul.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.lykkehjul.GameEvents
import com.example.lykkehjul.R
import com.example.lykkehjul.data.Memory
import com.example.lykkehjul.logic.LykkehjulLogic
import java.lang.StringBuilder

class PlayGame : AppCompatActivity() {

    lateinit var ord: TextView
    lateinit var tastBogstav: Button
    lateinit var gætBogstav: EditText
    private val gameManager = GameEvents()

    var lives = 5
    var visHemmeligOrd = ""
    var rigtigeGæt = mutableListOf<String>()
    var secretOrd = ""
    var hemmeligtOrd = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)


        /*val gameState = gameManager.startSpil()
        gameManager.hideWord(hemmeligtOrd)*/

        ord = findViewById(R.id.ord)


        // Load words
        val myDataset = Memory().loadWords()

        // Pick a random element from dataset
        hemmeligtOrd = myDataset.random().toString()

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
        gætBogstav = findViewById(R.id.gætBogstav)
        tastBogstav = findViewById(R.id.tastBogstav)


        // Spin wheel
        drejHjulKnap.setOnClickListener {

            // Get data for loadWheel
            val wheelOutcomes = Memory().loadWheel()
            // Set textView to random element from list
            val randomOutcome = wheelOutcomes.random().toString()
            wheelOutcomeDisplay.setText(randomOutcome)



        }

        guessLetter()

    }

    fun guessLetter() {
        tastBogstav.setOnClickListener {
            ord.setText(String(LykkehjulLogic.guessLetter(ord.text.toString(),
                                                          hemmeligtOrd,
                                                          gætBogstav.text.toString())))
        }
    }





}