package com.github.octoacademy.sedona.services

import com.github.octoacademy.sedona.models.Lodging
import com.github.octoacademy.sedona.repositories.LodgingRepository
import io.vavr.control.Try
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class LodgingService(val repository: LodgingRepository) {

    fun list(): Try<List<Lodging>> = transaction { repository.list() }

    fun create(lodging: Lodging): Try<Int> = transaction { repository.create(lodging) }

    fun read(id: Int): Try<Lodging> = transaction { repository.read(id) }

    fun update(id: Int, lodging: Lodging): Try<Lodging> = transaction { repository.update(id, lodging) }

    fun remove(id: Int): Try<Lodging> = transaction { repository.remove(id) }
}
