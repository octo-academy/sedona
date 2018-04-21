package com.github.octoacademy.sedona.models

enum class Type {
    HOTEL, MOTEL, APARTMENTS
}

data class Lodging(
        val name: String,
        val type: Type,
        val price: Int,
        val stars: Int,
        val rating: Double
)
