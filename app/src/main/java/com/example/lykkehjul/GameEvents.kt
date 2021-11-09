package com.example.lykkehjul

import com.example.lykkehjul.data.Datasource
import java.lang.StringBuilder

// Use this class to handle all event in the game

class GameEvents {

    private lateinit var underscoreWord1: String
    private lateinit var wordToGuess: String
    var underscoreWord = ""


    // Make each letter "_"
    fun hideWord(word: String) {
        val sb = StringBuilder()
        word.forEach {
            sb.append("_")
        }
        underscoreWord = sb.toString()

    }

    // Start the game
    fun startSpil() {

        val myDataset = Datasource().loadWords()
        var hemmeligtOrd = myDataset.random().toString()

        wordToGuess =  hemmeligtOrd
        //wordToGuess = GameConstants.words[randomIndex]
        hideWord(wordToGuess)


    }

}