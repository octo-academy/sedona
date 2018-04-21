package com.github.octoacademy.sedona.services

import com.github.octoacademy.sedona.models.Lodging
import com.github.octoacademy.sedona.repositories.LodgingRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
object LodgingService {

    fun list(): List<Lodging> = transaction { LodgingRepository.list() }

    fun create(lodging: Lodging): Lodging = transaction { LodgingRepository.create(lodging) }

    fun read(id: Int): Lodging? = transaction { LodgingRepository.read(id) }

    fun update(id: Int, lodging: Lodging): Lodging = transaction { LodgingRepository.update(id, lodging) }

    fun remove(id: Int) = transaction { LodgingRepository.remove(id) }
}
