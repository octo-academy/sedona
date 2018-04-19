package com.github.octoacademy.sedona.controllers

import com.github.octoacademy.sedona.models.Lodging
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
@RequestMapping("/lodgings")
class LodgingController {
    val lodgings = mutableListOf(
            Lodging("Amara-Resort & SPA", Type.HOTEL, 4000, StarRating.FOUR, 8.5),
            Lodging("Desert Quail Inn", Type.MOTEL, 3000, StarRating.THREE, 8.9),
            Lodging("Vila at Poco Diablo", Type.APARTMENTS, 2000, StarRating.TWO, 9.2)
    )

    @GetMapping
    fun list() : List<Lodging> {
        return lodgings
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody lodging : Lodging) : Lodging {
        lodgings.add(lodging)
        return lodging
    }

    @GetMapping("/{id}")
    @Throws(RuntimeException::class)
    fun read(@PathVariable id : Int) : Lodging? {
        return if (lodgings.size > id) lodgings[id] else null
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id : Int, @RequestBody lodging: Lodging) : Lodging {
        lodgings[id] = lodging
        return lodging
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id : Int) {
        lodgings.removeAt(id)
    }
}
