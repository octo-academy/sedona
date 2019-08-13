package com.github.octoacademy.sedona.module.db

import com.github.octoacademy.sedona.model.Error
import com.github.octoacademy.sedona.model.database.Lodging
import zio.ZIO

trait LodgingRepository {
  val repository: LodgingRepository.Service
}

object LodgingRepository {

  trait Service {

    def get(id: Long): ZIO[Any, Error, Lodging]

    def create(user: Lodging): ZIO[Any, Error, Lodging]

    def delete(id: Long): ZIO[Any, Error, Unit]
  }

}
