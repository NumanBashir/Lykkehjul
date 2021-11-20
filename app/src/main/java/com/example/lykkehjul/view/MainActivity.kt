package com.example.lykkehjul.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lykkehjul.R
import com.example.lykkehjul.data.Memory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setupActionBarWithNavController(findNavController(R.id.fragment))


        // Load words
        val myDataset = Memory().loadWords()
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

        startSpilKnap.setOnClickListener {

            val intent = Intent(this, PlayGame::class.java)
            startActivity(intent)



        }



    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}