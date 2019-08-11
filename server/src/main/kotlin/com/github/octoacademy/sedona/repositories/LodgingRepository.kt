package com.github.octoacademy.sedona.repositories

import com.github.octoacademy.sedona.models.Lodging
import com.github.octoacademy.sedona.schemas.Lodgings
import io.vavr.control.Try
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository
import java.sql.SQLException

@Repository
class LodgingRepository {

    fun list(): Try<List<Lodging>> =
            Try.of { Lodgings.selectAll().orderBy(Lodgings.name).map { fromRow(it) } }

    fun create(lodging: Lodging): Try<Int> =
            Try.of { Lodgings.insert(toRow(lodging)) get Lodgings.id }
               .map { id -> id!!.value }

    fun read(id: Int): Try<Lodging> =
            Try.of { Lodgings.select { Lodgings.id eq id }.map { fromRow(it) }.first() }

    fun update(id: Int, lodging: Lodging): Try<Lodging> =
            Try.of { Lodgings.update({ Lodgings.id eq id }, body = toRow(lodging)) }
               .filter({ updated -> updated > 0 }, { -> SQLException("No lodging with id $id exists") })
               .map { lodging }

    fun remove(id: Int): Try<Lodging> =
            read(id).flatMap { lodging -> Try.of { Lodgings.deleteWhere { Lodgings.id eq id } }.map { lodging } }

    private fun toRow(lodging: Lodging): Lodgings.(UpdateBuilder<*>) -> Unit = {
        it[Lodgings.name] = lodging.name
        it[Lodgings.type] = lodging.type
        it[Lodgings.price] = lodging.price
        it[Lodgings.stars] = lodging.stars
        it[Lodgings.rating] = lodging.rating.toBigDecimal()
    }

    private fun fromRow(row: ResultRow): Lodging {
        return Lodging(
                row[Lodgings.name],
                row[Lodgings.type],
                row[Lodgings.price],
                row[Lodgings.stars],
                row[Lodgings.rating].toDouble()
        )
    }
}
