package com.github.octoacademy.sedona.model.database

sealed trait Type

object Type {

  case object Hotel extends Type

  case object Motel extends Type

  case object Apartments extends Type

}
