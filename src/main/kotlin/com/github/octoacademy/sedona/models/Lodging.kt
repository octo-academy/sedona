package com.github.octoacademy.sedona.models

enum class StarRating {
    ONE, TWO, THREE, FOUR, FIVE
}

enum class Type {
    HOTEL, MOTEL, APARTMENTS
}

data class Lodging(
        val name: String,
        val type: Type,
        val price: Int,
        val stars: StarRating,
        val rating: Double
)
