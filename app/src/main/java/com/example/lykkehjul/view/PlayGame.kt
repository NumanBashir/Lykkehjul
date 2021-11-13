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
    lateinit var pointsNumber: TextView

    private val gameManager = GameEvents()

    var lives = 5
    var points = 0
    var visHemmeligOrd = ""
    var rigtigeGæt = mutableListOf<String>()
    var secretOrd = ""
    var hemmeligtOrd = ""
    var drejEllerIndtastState = true
    var randomOutcome = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)

        ord = findViewById(R.id.ord)
        pointsNumber = findViewById(R.id.pointsNumber)


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
            if (drejEllerIndtastState) {
                // Get data for loadWheel
                val wheelOutcomes = Memory().loadWheel()
                // Set textView to random element from list
                randomOutcome = wheelOutcomes.random().toString()
                wheelOutcomeDisplay.setText(randomOutcome)
                Toast.makeText(applicationContext,"Indtast venligst bogstav",Toast.LENGTH_SHORT).show()
                drejEllerIndtastState = false
            } else {
                Toast.makeText(applicationContext,"Du har ikke indtastet et bogstav",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun guessLetter() {
        tastBogstav.setOnClickListener {
            if (!drejEllerIndtastState) {
                if(TextUtils.isEmpty(gætBogstav.text.toString())) {
                    Toast.makeText(applicationContext,"Indtast et bogstav før du går videre",Toast.LENGTH_SHORT).show()
                } else {
                    val brugerIndtastet = gætBogstav.text.toString()
                    if (LykkehjulLogic.erBogstavIHemmeligOrd(hemmeligtOrd, brugerIndtastet)) {
                        /*if (randomOutcome.contains("1.000kr") ||
                            randomOutcome.contains("2.500kr") ||
                            randomOutcome.contains("5.000kr") ||
                            randomOutcome.contains("10.000kr") ||
                            randomOutcome.contains("500kr") ||
                            randomOutcome.contains("10kr")) {


                            pointsNumber.setText((pointsNumber.text.toString() + 1000))

                        }*/
                            if(randomOutcome.contains("1.000kr")) {
                                points += 1000
                                pointsNumber.setText(points.toString())
                            } else if(randomOutcome.contains("2.500kr")) {
                                points += 2500
                                pointsNumber.setText(points.toString())
                            } else if(randomOutcome.contains("5.000kr")) {
                                points += 5000
                                pointsNumber.setText(points.toString())
                            } else if(randomOutcome.contains("10.000kr")) {
                                points += 10000
                                pointsNumber.setText(points.toString())
                            } else if(randomOutcome.contains("500kr")) {
                                points += 500
                                pointsNumber.setText(points.toString())
                            } else if(randomOutcome.contains("10kr")) {
                                points += 10
                                pointsNumber.setText(points.toString())
                            }

                        ord.setText(String(LykkehjulLogic.guessLetter(ord.text.toString(), hemmeligtOrd, brugerIndtastet)))
                        gætBogstav.setText("")
                        Toast.makeText(applicationContext,"Drej venligst hjulet",Toast.LENGTH_SHORT).show()
                    }


                    drejEllerIndtastState = true
                }
            } else {
                Toast.makeText(applicationContext,"Du har ikke drejet hjulet endnu",Toast.LENGTH_SHORT).show()
            }


        }
    }





}