package com.github.octoacademy.sedona.controllers

import com.github.octoacademy.sedona.models.Lodging
import com.github.octoacademy.sedona.services.LodgingService
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

    @GetMapping
    fun list() : List<Lodging> {
        return LodgingService.list()
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody lodging : Lodging) : Lodging {
        return LodgingService.create(lodging)
    }

    @GetMapping("/{id}")
    @Throws(RuntimeException::class)
    fun read(@PathVariable id : Int) : Lodging? {
        return LodgingService.read(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id : Int, @RequestBody lodging: Lodging) : Lodging {
        return LodgingService.update(id, lodging)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id : Int) {
        LodgingService.remove(id)
    }
}
