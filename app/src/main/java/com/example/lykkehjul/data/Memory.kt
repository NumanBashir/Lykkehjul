package com.example.lykkehjul.data

import com.example.lykkehjul.model.Words

class Memory {

    fun loadWords(): List<Words> {
        return listOf<Words>(
            //Words("kotlin"),
            //Words("java"),
            //Words("python"),
            //Words("swift")
            //Words("tiger"),
            Words("hah"),
            //Words("fulg"),
            //Words("bjørn"),
            //Words("krokodille"),
            //Words("slange"),
            //Words("edderkop"),

        )

    }

    fun loadWheel(): List<Words> {
        return listOf<Words>(
            Words("1.000kr"),
            Words("2.500kr"),
            Words("5.000kr"),
            Words("10.000kr"),
            Words("500kr"),
            Words("10kr"),
            Words("Tabt Tur"),
            Words("Ekstra Tur"),
            Words("Fallit"),

        )
    }

}