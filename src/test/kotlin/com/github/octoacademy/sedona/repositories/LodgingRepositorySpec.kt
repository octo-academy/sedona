package com.github.octoacademy.sedona.repositories

import com.github.octoacademy.sedona.models.Lodging
import com.github.octoacademy.sedona.models.Type
import com.github.octoacademy.sedona.schemas.Lodgings
import org.amshove.kluent.`should be in`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should contain all`
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not be in`
import org.amshove.kluent.`should throw`
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.ActionBody
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.TestBody
import org.jetbrains.spek.api.dsl.TestContainer
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it as originalIt
import org.jetbrains.spek.api.dsl.on as originalOn
import java.sql.SQLException

class LodgingRepositorySpec : Spek({

    val lodgingRepository = LodgingRepository()

    val hotelCalifornia = Lodging("Hotel California", Type.HOTEL, 999, 3, 7.8)
    val grandBudapest = Lodging("Grand Budapest", Type.APARTMENTS, 5000, 5, 9.8)
    val mariposaSaloon = Lodging("Mariposa Saloon", Type.MOTEL, 5000, 5, 8.9)

    val invalidLodging = Lodging("", Type.MOTEL, -42, 42, 42.42)

    /**
     * Creates an [action][SpecBody.action] where body wrapped with transaction.
     */
    fun SpecBody.on(description: String, body: ActionBody.() -> Unit) {
        originalOn(description) { transaction { body() } }
    }

    /**
     * Creates a [test][SpecBody.test] where body wrapped with transaction.
     */
    fun TestContainer.it(description: String, body: TestBody.() -> Unit) {
        originalIt(description) { transaction { body() } }
    }

    given("a lodging repository") {

        beforeGroup {
            val db = createTempFile("sedona-backend", ".db").apply { deleteOnExit() }
            Database.connect(url = "jdbc:h2:${db.absolutePath}", driver = "org.h2.Driver")
        }

        beforeEachTest {
            transaction {
                SchemaUtils.drop(Lodgings)
                SchemaUtils.create(Lodgings)
            }
        }

        on("attempting to create invalid lodging") {
            val attemptToCreate = { lodgingRepository.create(invalidLodging) }

            it("should throw exception") {
                attemptToCreate `should throw` SQLException::class
            }

            it("should not appear in the lodgings list") {
                invalidLodging `should not be in` lodgingRepository.list()
            }
        }

        on("attempting to create lodging if one already exists") {
            lodgingRepository.create(hotelCalifornia)
            val attemptToCreate = { lodgingRepository.create(hotelCalifornia) }

            it("should throw exception") {
                attemptToCreate `should throw` SQLException::class
            }

            it("should not double lodgings") {
                lodgingRepository.list() `should equal` listOf(hotelCalifornia)
            }
        }

        on("creating new lodging") {
            val id = lodgingRepository.create(hotelCalifornia)

            it("should return id of created lodging") {
                lodgingRepository.read(id) `should equal` hotelCalifornia
            }

            it("should appear in the lodgings list") {
                hotelCalifornia `should be in` lodgingRepository.list()
            }
        }

        on("attempting to read lodging with id that doesn't exist") {
            val hotelCaliforniaId = lodgingRepository.create(hotelCalifornia)
            val grandBudapestId = lodgingRepository.create(grandBudapest)
            val nonExistentId = hotelCaliforniaId + grandBudapestId

            it("should return null") {
                lodgingRepository.read(nonExistentId) `should be` null
            }
        }

        on("reading lodging") {
            val id = lodgingRepository.create(hotelCalifornia)

            it("should return lodging") {
                lodgingRepository.read(id) `should equal` hotelCalifornia
            }
        }

        on("attempting to update lodging with id that doesn't exist") {
            val hotelCaliforniaId = lodgingRepository.create(hotelCalifornia)
            val grandBudapestId = lodgingRepository.create(grandBudapest)
            val nonExistentId = hotelCaliforniaId + grandBudapestId
            val updated = lodgingRepository.update(nonExistentId, mariposaSaloon)

            it("should return false") {
                updated `should be` false
            }

            it("should not affect existing lodgings") {
                lodgingRepository.list() `should contain all` listOf(hotelCalifornia, grandBudapest)
            }

            it("should not appear in the lodging list") {
                mariposaSaloon `should not be in` lodgingRepository.list()
            }
        }

        on("attempting to update lodging with invalid one") {
            val hotelCaliforniaId = lodgingRepository.create(hotelCalifornia)
            lodgingRepository.create(grandBudapest)
            lodgingRepository.create(mariposaSaloon)
            val attemptToUpdate = { lodgingRepository.update(hotelCaliforniaId, invalidLodging) }

            it("should throw exception") {
                attemptToUpdate `should throw` SQLException::class
            }

            it("should not affect existing lodgings") {
                lodgingRepository.list() `should contain all` listOf(hotelCalifornia, grandBudapest, mariposaSaloon)
            }

            it("should not appear in the lodging list") {
                invalidLodging `should not be in` lodgingRepository.list()
            }
        }

        on("updating lodging") {
            val id = lodgingRepository.create(hotelCalifornia)
            val updated = lodgingRepository.update(id, grandBudapest)

            it("should return true") {
                updated `should be` true
            }

            it("should provide new lodging on reading") {
                lodgingRepository.read(id) `should equal` grandBudapest
            }
        }

        on("attempting to remove lodging with id that doesn't exist") {
            val hotelCaliforniaId = lodgingRepository.create(hotelCalifornia)
            val grandBudapestId = lodgingRepository.create(grandBudapest)
            val mariposaSaloonId = lodgingRepository.create(mariposaSaloon)
            val nonExistentId = hotelCaliforniaId + grandBudapestId + mariposaSaloonId
            val removed = lodgingRepository.remove(nonExistentId)

            it("should return false") {
                removed `should be` false
            }

            it("should not affect existing lodgings") {
                lodgingRepository.list() `should contain all` listOf(hotelCalifornia, grandBudapest, mariposaSaloon)
            }
        }

        on("removing lodging") {
            val id = lodgingRepository.create(hotelCalifornia)
            lodgingRepository.create(grandBudapest)
            lodgingRepository.create(mariposaSaloon)
            val removed = lodgingRepository.remove(id)

            it("should return true") {
                removed `should be` true
            }

            it("should remove lodging under given id") {
                hotelCalifornia `should not be in` lodgingRepository.list()
            }

            it("should not affect other lodgings") {
                lodgingRepository.list() `should contain all` listOf(grandBudapest, mariposaSaloon)
            }
        }

        on("listing all lodgings when there are no lodgings yet") {
            it("should return an empty list") {
                lodgingRepository.list() `should equal` emptyList()
            }
        }

        on("listing all lodgings when there are lodgings exist") {
            lodgingRepository.create(hotelCalifornia)
            lodgingRepository.create(grandBudapest)
            lodgingRepository.create(mariposaSaloon)

            it("should return list of all the lodgings") {
                lodgingRepository.list() `should contain all` listOf(hotelCalifornia, grandBudapest, mariposaSaloon)
            }
        }
    }
})
