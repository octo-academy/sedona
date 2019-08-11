package com.github.octoacademy.sedona.schemas

import com.github.octoacademy.sedona.models.Type
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.math.BigDecimal

object Lodgings : IntIdTable() {
    val name: Column<String> = varchar("name", 200).uniqueIndex()
    val type: Column<Type> = enumerationByName("type", 20, Type::class.java)
    val price: Column<Int> = integer("price")
    val stars: Column<Int> = integer("stars")
    val rating: Column<BigDecimal> = decimal("rating", 2, 1)
}
