package com.github.octoacademy.sedona.module.logger

import com.github.octoacademy.sedona.model.Error
import zio.ZIO

trait Logger {
  val logger: Logger.Service
}

object Logger {

  trait Service {
    def error(message: => String): ZIO[Any, Error, Unit]

    def warn(message: => String): ZIO[Any, Error, Unit]

    def info(message: => String): ZIO[Any, Error, Unit]

    def debug(message: => String): ZIO[Any, Error, Unit]

    def trace(message: => String): ZIO[Any, Error, Unit]

    def error(t: Throwable)(message: => String): ZIO[Any, Error, Unit]

    def warn(t: Throwable)(message: => String): ZIO[Any, Error, Unit]

    def info(t: Throwable)(message: => String): ZIO[Any, Error, Unit]

    def debug(t: Throwable)(message: => String): ZIO[Any, Error, Unit]

    def trace(t: Throwable)(message: => String): ZIO[Any, Error, Unit]
  }
}
