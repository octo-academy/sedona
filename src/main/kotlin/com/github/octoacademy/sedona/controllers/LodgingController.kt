package com.github.octoacademy.sedona.controllers

import com.github.octoacademy.sedona.models.Lodging
import io.vavr.control.Try
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
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
import java.net.URI

@RestController
@RequestMapping("/lodgings")
class LodgingController(val service: LodgingService) {

    @GetMapping
    fun list(): List<Lodging> {
        return service.list()
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody lodging: Lodging): ResponseEntity<Lodging> {
        val id = service.create(lodging)
        return created(URI.create("/lodgings/$id")).body(lodging)
    }

    @GetMapping("/{id}")
    fun read(@PathVariable id: Int): ResponseEntity<Lodging> {
        return Try.of { service.read(id) }
                .filter { it != null }
                .map { ok().body<Lodging>(it) }
                .getOrElse { notFound().build<Lodging>() }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody lodging: Lodging): ResponseEntity<Lodging> {
        return ok().body(service.update(id, lodging))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Nothing> {
        return Try.of { service.remove(id) }
                .map { noContent().build<Nothing>() }
                .getOrElse { notFound().build<Nothing>() }
    }
}
