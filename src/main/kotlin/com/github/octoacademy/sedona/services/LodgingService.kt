package com.github.octoacademy.sedona.services

import com.github.octoacademy.sedona.models.Lodging
import com.github.octoacademy.sedona.models.StarRating
import com.github.octoacademy.sedona.models.Type
import org.springframework.stereotype.Service

@Service
object LodgingService {
    private val lodgings = mutableListOf(
            Lodging("Amara-Resort & SPA", Type.HOTEL, 4000, StarRating.FOUR, 8.5),
            Lodging("Desert Quail Inn", Type.MOTEL, 3000, StarRating.THREE, 8.9),
            Lodging("Vila at Poco Diablo", Type.APARTMENTS, 2000, StarRating.TWO, 9.2)
    )

    fun list(): List<Lodging> = lodgings

    fun create(lodging: Lodging): Lodging {
        lodgings.add(lodging)
        return lodging
    }

    fun read(id : Int): Lodging? = if (lodgings.size > id) lodgings[id] else null

    fun update(id: Int, lodging: Lodging): Lodging {
        lodgings[id] = lodging
        return lodging
    }

    fun remove(id: Int) = lodgings.removeAt(id)
}
