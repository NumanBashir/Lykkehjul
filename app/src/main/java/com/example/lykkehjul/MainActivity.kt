package com.example.lykkehjul

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lykkehjul.adapter.ItemAdapter
import com.example.lykkehjul.data.Datasource
import com.example.lykkehjul.model.Words
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDataset = Datasource().loadWords()
        println(myDataset)
        println(myDataset[0].toString())

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this, myDataset)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<Words>()
        data.add(Words("kotlin"))
        println(data.toString())


        recyclerView.setHasFixedSize(true)
        //recyclerView.visibility = View.GONE


        val words2 = listOf(myDataset)
        println(words2.toString())
        Log.i("list: ", words2.toString())



        // List words
        val words = listOf("kotlin", "java", "python", "swift")
        val randomElement = words.random()
        println(randomElement)
        Log.i("random element from array", randomElement)

        // Convert string into CharArray
        val charsOfWord: CharArray = randomElement.toCharArray()
        println(charsOfWord.contentToString())

        val startSpilKnap: Button = findViewById(R.id.startSpilKnap)
        val ordTilView: TextView = findViewById(R.id.ordTilView)

        //var antalLiv: TextView = findViewById(R.id.antalLiv)

        startSpilKnap.setOnClickListener {
            //ordTilView.setText(randomElement)
            startSpilKnap.setText("Drej hjulet!")

            ordTilView.setText(myDataset[0].toString())

            /*ordTilView.setText("")
            for(i in charsOfWord) {
                ordTilView.append("$i ")
            }*/


            //antalLiv = antalLiv - 1

            //Make the word invisible
            //ordTilView.visibility = View.GONE

            /* val names = listOf("Henrik", "Lars", "Mogens", "Cristiano Ronaldo")
            val randomElement2 = names.random()
            ordTilView.setText(randomElement2)
             */


        }



    }
}