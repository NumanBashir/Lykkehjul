package com.example.lykkehjul.data

import com.example.lykkehjul.model.Words

class Datasource {

    fun loadWords(): List<Words> {
        return listOf<Words>(
            Words("kotlin"),
            Words("java"),
            Words("python"),
            Words("swift")
        )

    }
}