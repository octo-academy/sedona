package com.github.octoacademy.sedona

import com.github.octoacademy.sedona.schemas.Lodgings
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Bean
    fun database(@Value("#{environment.DATABASE_URL}") url: String,
                 @Value("#{environment.DATABASE_DRIVER}") driver: String): Database {

        val database = Database.connect(url, driver = driver)
        transaction {
            SchemaUtils.createMissingTablesAndColumns(Lodgings)
        }
        return database
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
