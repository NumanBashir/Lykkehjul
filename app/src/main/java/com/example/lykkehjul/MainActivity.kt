package com.example.lykkehjul

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

        // Load words
        val myDataset = Datasource().loadWords()
        println(myDataset)

        // Pick a random element from dataset
        val randomElement2 = myDataset.random().toString()

        // Seperate each letter into new char array
        val charsOfWord2: CharArray = randomElement2.toCharArray()
        println(charsOfWord2.contentToString())

        /*val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this, myDataset)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.setHasFixedSize(true)
        recyclerView.visibility = View.GONE */


        val startSpilKnap: Button = findViewById(R.id.startSpilKnap)
        val ordTilView: TextView = findViewById(R.id.ordTilView)

        //var antalLiv: TextView = findViewById(R.id.antalLiv)

        startSpilKnap.setOnClickListener {
            startSpilKnap.setText("Drej hjulet!")
            //ordTilView.setText(randomElement2)

            ordTilView.setText("")
            for(i in charsOfWord2) {
                ordTilView.append("$i ")
            }


            //antalLiv = antalLiv - 1

            //Make the word invisible
            //ordTilView.visibility = View.GONE


        }



    }
}