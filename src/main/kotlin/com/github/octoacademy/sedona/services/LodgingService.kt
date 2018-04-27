package com.github.octoacademy.sedona.services

import com.github.octoacademy.sedona.models.Lodging
import com.github.octoacademy.sedona.repositories.LodgingRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class LodgingService(val repository: LodgingRepository) {

    fun list(): List<Lodging> = transaction { repository.list() }

    fun create(lodging: Lodging): Lodging = transaction { repository.create(lodging) }

    fun read(id: Int): Lodging? = transaction { repository.read(id) }

    fun update(id: Int, lodging: Lodging): Lodging = transaction { repository.update(id, lodging) }

    fun remove(id: Int) = transaction { repository.remove(id) }
}
