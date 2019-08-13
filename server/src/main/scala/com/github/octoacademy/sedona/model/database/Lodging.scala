package com.github.octoacademy.sedona.model.database

case class Lodging(
  id: Long,
  name: String,
  `type`: Type,
  price: Int,
  stars: Int,
  rating: Double
)
