package com.github.octoacademy.sedona.controllers

import com.github.octoacademy.sedona.models.Accommodation
import com.github.octoacademy.sedona.models.StarRating
import com.github.octoacademy.sedona.models.Type
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accommodations")
class AccommodationController {
    val accommodations = mutableListOf(
            Accommodation("Amara-Resort & SPA", Type.HOTEL, 4000, StarRating.FOUR, 8.5),
            Accommodation("Desert Quail Inn", Type.MOTEL, 3000, StarRating.THREE, 8.9),
            Accommodation("Vila at Poco Diablo", Type.APARTMENTS, 2000, StarRating.TWO, 9.2)
    )

    @GetMapping
    fun list() : List<Accommodation> {
        return accommodations
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody accommodation : Accommodation) : Accommodation {
        accommodations.add(accommodation)
        return accommodation
    }

    @GetMapping("/{id}")
    @Throws(RuntimeException::class)
    fun read(@PathVariable id : Int) : Accommodation? {
        return if (accommodations.size > id) accommodations[id] else null
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id : Int, @RequestBody accommodation: Accommodation) : Accommodation {
        accommodations[id] = accommodation
        return accommodation
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id : Int) {
        accommodations.removeAt(id)
    }
}
