package com.github.octoacademy.sedona.module

import com.github.octoacademy.sedona.model.Error
import com.github.octoacademy.sedona.model.database.{Lodging, Type}
import io.getquill.MappedEncoding
import zio.ZIO

package object db {

  def get(id: Long): ZIO[LodgingRepository, Error, Lodging] =
    ZIO.accessM(_.repository.get(id))

  def create(user: Lodging): ZIO[LodgingRepository, Error, Lodging] =
    ZIO.accessM(_.repository.create(user))

  def delete(id: Long): ZIO[LodgingRepository, Error, Unit] =
    ZIO.accessM(_.repository.delete(id))

  implicit val encodeType: MappedEncoding[Type, String] = MappedEncoding[Type, String](_.getClass.getSimpleName)

  implicit val decodeType: MappedEncoding[String, Type] = MappedEncoding[String, Type] {
    case "Hotel" => Type.Hotel
    case "Motel" => Type.Motel
    case "Apartments" => Type.Apartments
  }
}
