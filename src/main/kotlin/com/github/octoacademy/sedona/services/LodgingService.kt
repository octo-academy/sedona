package com.github.octoacademy.sedona.services

import com.github.octoacademy.sedona.models.Lodging
import com.github.octoacademy.sedona.repositories.LodgingRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class LodgingService(val repository: LodgingRepository) {

    fun list(): List<Lodging> = transaction { repository.list().get() }

    fun create(lodging: Lodging): Int = transaction { repository.create(lodging).get() }

    fun read(id: Int): Lodging? = transaction { repository.read(id).get() }

    fun update(id: Int, lodging: Lodging): Lodging = transaction { repository.update(id, lodging).get() }

    fun remove(id: Int): Lodging = transaction { repository.remove(id).get() }
}
