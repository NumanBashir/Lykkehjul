package com.example.lykkehjul.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lykkehjul.GameEvents
import com.example.lykkehjul.R
import com.example.lykkehjul.data.Memory
import com.example.lykkehjul.logic.LykkehjulLogic
import java.lang.StringBuilder

class PlayGame : AppCompatActivity() {

    lateinit var ord: TextView
    lateinit var tastBogstav: Button
    lateinit var gætBogstav: EditText
    lateinit var drejHjulKnap: Button
    lateinit var wheelOutcomeDisplay: TextView

    private val gameManager = GameEvents()

    var lives = 5
    var visHemmeligOrd = ""
    var rigtigeGæt = mutableListOf<String>()
    var secretOrd = ""
    var hemmeligtOrd = ""
    var drejEllerIndtast = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)

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

        drejHjulKnap = findViewById(R.id.drejHjul)
        wheelOutcomeDisplay = findViewById(R.id.wheelOutcome)
        gætBogstav = findViewById(R.id.gætBogstav)
        tastBogstav = findViewById(R.id.tastBogstav)


        drejHjul()
        guessLetter()

    }

    fun drejHjul() {
        // Spin wheel
        drejHjulKnap.setOnClickListener {
            if (drejEllerIndtast) {
                // Get data for loadWheel
                val wheelOutcomes = Memory().loadWheel()
                // Set textView to random element from list
                val randomOutcome = wheelOutcomes.random().toString()
                wheelOutcomeDisplay.setText(randomOutcome)
                Toast.makeText(applicationContext,"Indtast venligst bogstav",Toast.LENGTH_SHORT).show()
                drejEllerIndtast = false
            } else {
                Toast.makeText(applicationContext,"Du har ikke indtastet et bogstav",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun guessLetter() {
        tastBogstav.setOnClickListener {
            if (!drejEllerIndtast) {
                if(TextUtils.isEmpty(gætBogstav.text.toString())) {
                    Toast.makeText(applicationContext,"Indtast et bogstav før du går videre",Toast.LENGTH_SHORT).show()
                } else {
                    ord.setText(String(LykkehjulLogic.guessLetter(ord.text.toString(), hemmeligtOrd, gætBogstav.text.toString())))
                    gætBogstav.setText("")
                    Toast.makeText(applicationContext,"Drej venligst hjulet",Toast.LENGTH_SHORT).show()
                    drejEllerIndtast = true
                }
            } else {
                Toast.makeText(applicationContext,"Du har ikke drejet hjulet endnu",Toast.LENGTH_SHORT).show()
            }


        }
    }





}