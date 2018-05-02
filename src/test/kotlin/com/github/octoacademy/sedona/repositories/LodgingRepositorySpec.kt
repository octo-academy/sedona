package com.github.octoacademy.sedona.repositories

import com.github.octoacademy.sedona.models.Lodging
import com.github.octoacademy.sedona.models.Type
import com.github.octoacademy.sedona.schemas.Lodgings
import org.amshove.kluent.`should be in`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not be in`
import org.amshove.kluent.`should throw`
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.sql.SQLException

class LodgingRepositorySpec : Spek({

    describe("LodgingRepository") {
        val lodgingRepository = LodgingRepository()

        val hotelCalifornia = Lodging("Hotel California", Type.HOTEL, 999, 3, 7.8)
        val grandBudapest = Lodging("Grand Budapest", Type.APARTMENTS, 5000, 5, 9.8)

        val invalidLodging = Lodging("", Type.MOTEL, -42, 42, 42.42)

        beforeGroup {
            val db = createTempFile("sedona-backend", ".db").apply { deleteOnExit() }
            Database.connect(url = "jdbc:h2:${db.absolutePath}", driver = "org.h2.Driver")
        }

        beforeEachTest { transaction {
            SchemaUtils.drop(Lodgings)
            SchemaUtils.create(Lodgings)
        } }

        on("creating new lodging") {
            it("should throw exception when lodging object is invalid") { transaction {
                val attemptToCreate = { lodgingRepository.create(invalidLodging) }
                attemptToCreate `should throw` SQLException::class
                lodgingRepository.list() `should equal` emptyList()
            } }

            it("should return id of created lodging when lodging object is valid") { transaction {
                val id = lodgingRepository.create(hotelCalifornia)
                lodgingRepository.read(id) `should equal` hotelCalifornia
            } }
        }

        on("reading lodging") {
            it("should return null when lodging with given id doesn't exist") { transaction {
                lodgingRepository.read(1) `should be` null
            } }

            it("should return lodging when lodging with given id exists") { transaction {
                val id = lodgingRepository.create(hotelCalifornia)
                lodgingRepository.read(id) `should equal` hotelCalifornia
            } }
        }

        on("updating lodging") {
            it("should throw exception when lodging with given id doesn't exist") { transaction {
                val attemptToUpdate = { lodgingRepository.update(1, hotelCalifornia) }
                attemptToUpdate `should throw` SQLException::class
            } }

            it("should return updated lodging when one with given id exists") { transaction {
                val id = lodgingRepository.create(hotelCalifornia)
                val updated = lodgingRepository.update(id, grandBudapest)
                updated `should equal` grandBudapest
            } }

            it("should replace existent lodging with new one when given id exists") { transaction {
                val id = lodgingRepository.create(hotelCalifornia)
                lodgingRepository.update(id, grandBudapest)
                val allTheLodgings = lodgingRepository.list()
                grandBudapest `should be in` allTheLodgings
                hotelCalifornia `should not be in` allTheLodgings
            } }
        }

        on("removing lodging") {
            it("should return false when such lodging didn't found") { transaction {
                lodgingRepository.remove(1) `should be` false
            } }

            it("should return true when such lodging was found") { transaction {
                val id = lodgingRepository.create(hotelCalifornia)
                hotelCalifornia `should be in` lodgingRepository.list()
                lodgingRepository.remove(id) `should be` true
            } }

            it("should remove lodging when one exists") { transaction {
                val id = lodgingRepository.create(grandBudapest)
                grandBudapest `should be in` lodgingRepository.list()
                lodgingRepository.remove(id)
                hotelCalifornia `should not be in` lodgingRepository.list()
            } }
        }

        on("listing all lodgings") {
            it("should return an empty list when there are no lodgings yet") { transaction {
                lodgingRepository.list() `should equal` emptyList()
            } }

            it("should return all the lodgings when there are lodgings exists") { transaction {
                lodgingRepository.create(hotelCalifornia)
                lodgingRepository.create(grandBudapest)
                val allTheLodgings = lodgingRepository.list()
                hotelCalifornia `should be in` allTheLodgings
                grandBudapest `should be in` allTheLodgings
            } }
        }
    }
})
