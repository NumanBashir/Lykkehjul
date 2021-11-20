package com.example.lykkehjul.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lykkehjul.GameEvents
import com.example.lykkehjul.R
import com.example.lykkehjul.TabtFragment
import com.example.lykkehjul.VundetFragment
import com.example.lykkehjul.adapter.ItemAdapter
import com.example.lykkehjul.data.Memory
import com.example.lykkehjul.logic.LykkehjulLogic
import com.example.lykkehjul.model.Words

class PlayGame : AppCompatActivity() {

    lateinit var ord: RecyclerView
    lateinit var tastBogstav: Button
    lateinit var gætBogstav: EditText
    lateinit var drejHjulKnap: Button
    lateinit var wheelOutcomeDisplay: TextView
    lateinit var pointsNumber: TextView
    lateinit var antalLiv: TextView
    lateinit var nyRecyclerView: RecyclerView
    lateinit var OpenFragment: Button

    private val gameManager = GameEvents()

    var lives = 3
    var points = 0
    var visHemmeligOrd = ""
    var underScoreOrd = mutableListOf<Words>()
    var secretOrd = ""
    var hemmeligtOrd = ""
    var drejEllerIndtastState = true
    var randomOutcome = ""

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ItemAdapter.ViewHolder>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)

        nyRecyclerView = findViewById(R.id.nyRecyclerView)

        layoutManager = LinearLayoutManager(this)
        nyRecyclerView.layoutManager = layoutManager

        //adapter = ItemAdapter()
        nyRecyclerView.adapter = adapter

        //Ord fra textview
        ord = findViewById(R.id.nyRecyclerView)

        pointsNumber = findViewById(R.id.pointsNumber)
        antalLiv = findViewById(R.id.antalLiv)


        // Load words
        val myDataset = Memory().loadWords()

        // Pick a random element from dataset
        hemmeligtOrd = myDataset.random().toString()


        Toast.makeText(applicationContext,"Drej venligst hjulet",Toast.LENGTH_SHORT).show()

        // Set textView to the random word
        //ord.setText(secretOrd)

        drejHjulKnap = findViewById(R.id.drejHjul)
        wheelOutcomeDisplay = findViewById(R.id.wheelOutcome)
        gætBogstav = findViewById(R.id.gætBogstav)
        tastBogstav = findViewById(R.id.tastBogstav)


        val charArray = hemmeligtOrd.toCharArray()

        val data: MutableList<Words> = ArrayList()
        for(i in charArray) {
            data.add(Words("_"))

        }

        underScoreOrd = data

        layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        adapter = ItemAdapter(data)

        nyRecyclerView.layoutManager = layoutManager
        nyRecyclerView.setHasFixedSize(true)
        nyRecyclerView.adapter = adapter


        drejHjul()
        guessLetter()

        // DENNE FRAGMENT SKAL ORDNES
        /*if(underScoreOrd.contains("_")) {
            val dialog2 = VundetFragment()
            dialog2.show(supportFragmentManager, "customDialog")
        }*/



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

                // If spin wheel lands on any of the threes, then lives changes
                if(randomOutcome.contains("Tabt Tur")) {
                    Toast.makeText(applicationContext,"Du mistede et liv, drej hjulet igen",Toast.LENGTH_SHORT).show()
                    lives -= 1
                    antalLiv.setText(lives.toString())
                } else if(randomOutcome.contains("Ekstra Tur")) {
                    Toast.makeText(applicationContext,"Du fik et ekstra liv, drej hjulet igen",Toast.LENGTH_SHORT).show()
                    lives += 1
                    antalLiv.setText(lives.toString())
                } else if(randomOutcome.contains("Fallit")) {
                    Toast.makeText(applicationContext,"Du ramte fallit, du tabte",Toast.LENGTH_SHORT).show()
                    lives = 0
                    antalLiv.setText(lives.toString())
                } else {
                    // if not any of those, then change state
                    Toast.makeText(applicationContext,"Indtast venligst bogstav",Toast.LENGTH_SHORT).show()
                    drejEllerIndtastState = false
                }

            } else {
                // If user has not entered a letter
                Toast.makeText(applicationContext,"Du har ikke indtastet et bogstav",Toast.LENGTH_SHORT).show()
            }

            if(lives == 0) {
                val dialog = TabtFragment()
                dialog.show(supportFragmentManager,"customDialog")
            }

            if(!underScoreOrd.contains(Words("_"))) {
                val dialog = VundetFragment()
                dialog.show(supportFragmentManager, "customDialog")
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
                            if(randomOutcome.contains("1.000kr")) {
                                points += 1000 * LykkehjulLogic.fårAntalDuplikeretBogstav(hemmeligtOrd,brugerIndtastet)
                                pointsNumber.setText(points.toString())
                            } else if(randomOutcome.contains("2.500kr")) {
                                points += 2500 * LykkehjulLogic.fårAntalDuplikeretBogstav(hemmeligtOrd,brugerIndtastet)
                                pointsNumber.setText(points.toString())
                            } else if(randomOutcome.contains("5.000kr")) {
                                points += 5000 * LykkehjulLogic.fårAntalDuplikeretBogstav(hemmeligtOrd,brugerIndtastet)
                                pointsNumber.setText(points.toString())
                            } else if(randomOutcome.contains("10.000kr")) {
                                points += 10000 * LykkehjulLogic.fårAntalDuplikeretBogstav(hemmeligtOrd,brugerIndtastet)
                                pointsNumber.setText(points.toString())
                            } else if(randomOutcome.contains("500kr")) {
                                points += 500 * LykkehjulLogic.fårAntalDuplikeretBogstav(hemmeligtOrd,brugerIndtastet)
                                pointsNumber.setText(points.toString())
                            } else if(randomOutcome.contains("10kr")) {
                                points += 10 * LykkehjulLogic.fårAntalDuplikeretBogstav(hemmeligtOrd,brugerIndtastet)
                                pointsNumber.setText(points.toString())
                            }
                        gætBogstav.setText("")

                        var setUnderscoreOrd = ""
                        for (words in underScoreOrd) {
                            setUnderscoreOrd += words
                        }
                        val charArray = LykkehjulLogic.guessLetter(setUnderscoreOrd, hemmeligtOrd, brugerIndtastet)

                        val data: MutableList<Words> = ArrayList()
                        for(i in charArray) {
                            data.add(Words("$i"))

                        }

                        underScoreOrd = data

                        layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
                        adapter = ItemAdapter(data)

                        if(!setUnderscoreOrd.contains("_")) {
                            val dialog2 = VundetFragment()
                            dialog2.show(supportFragmentManager, "customDialog")
                        }

                        nyRecyclerView.layoutManager = layoutManager
                        nyRecyclerView.setHasFixedSize(true)
                        nyRecyclerView.adapter = adapter

                        Toast.makeText(applicationContext,"Drej venligst hjulet",Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(applicationContext,"Bostavet var der ikke, du mister et liv, drej hjulet igen ",Toast.LENGTH_LONG).show()
                        lives -= 1
                        antalLiv.setText(lives.toString())
                        gætBogstav.setText("")


                    }


                    drejEllerIndtastState = true
                }
            } else {
                Toast.makeText(applicationContext,"Du har ikke drejet hjulet endnu",Toast.LENGTH_SHORT).show()
            }

            if(lives == 0) {
                val dialog = TabtFragment()
                dialog.show(supportFragmentManager,"customDialog")
            }

            if(!underScoreOrd.contains(Words("_"))) {
                val dialog = VundetFragment()
                dialog.show(supportFragmentManager, "customDialog")
            }




        }

        /*if(underScoreOrd.contains("_")) {
            val dialog2 = VundetFragment()
            dialog2.show(supportFragmentManager, "customDialog2")
        }*/

    }





}