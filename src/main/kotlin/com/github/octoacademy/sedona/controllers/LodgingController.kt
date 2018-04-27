package com.github.octoacademy.sedona.controllers

import io.vavr.control.Try
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import com.github.octoacademy.sedona.services.LodgingService

@RestController
@RequestMapping("/lodgings")
class LodgingController(val service: LodgingService) {

    @GetMapping
    fun list(): List<com.github.octoacademy.sedona.models.Lodging> {
        return service.list()
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody lodging: com.github.octoacademy.sedona.models.Lodging): com.github.octoacademy.sedona.models.Lodging {
        return service.create(lodging)
    }

    @GetMapping("/{id}")
    fun read(@PathVariable id: Int): ResponseEntity<com.github.octoacademy.sedona.models.Lodging> {
        return Try.of { service.read(id) }
                .filter { it != null }
                .map { ok().body<com.github.octoacademy.sedona.models.Lodging>(it) }
                .getOrElse { notFound().build<com.github.octoacademy.sedona.models.Lodging>() }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody lodging: com.github.octoacademy.sedona.models.Lodging): ResponseEntity<com.github.octoacademy.sedona.models.Lodging> {
        return Try.of { service.update(id, lodging) }
                .map { ok().body(it) }
                .getOrElse { notFound().build() }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Nothing> {
        return Try.of { service.remove(id) }
                .map { noContent().build<Nothing>() }
                .getOrElse { notFound().build<Nothing>() }
    }
}
