package com.github.octoacademy.sedona.module.logger

import com.github.octoacademy.sedona.model.Error
import zio._

trait ConsoleLogger extends Logger {

  val logger: Logger.Service = new Logger.Service {
    def error(message: => String): ZIO[Any, Error, Unit] = UIO.effectTotal(println(message))

    def warn(message: => String): ZIO[Any, Error, Unit] = UIO.effectTotal(println(message))

    def info(message: => String): ZIO[Any, Error, Unit] = UIO.effectTotal(println(message))

    def debug(message: => String): ZIO[Any, Error, Unit] = UIO.effectTotal(println(message))

    def trace(message: => String): ZIO[Any, Error, Unit] = UIO.effectTotal(println(message))

    def error(t: Throwable)(message: => String): ZIO[Any, Error, Unit] = UIO.effectTotal {
      t.printStackTrace()
      println(message)
    }

    def warn(t: Throwable)(message: => String): ZIO[Any, Error, Unit] = UIO.effectTotal {
      t.printStackTrace()
      println(message)
    }

    def info(t: Throwable)(message: => String): ZIO[Any, Error, Unit] = UIO.effectTotal {
      t.printStackTrace()
      println(message)
    }

    def debug(t: Throwable)(message: => String): ZIO[Any, Error, Unit] = UIO.effectTotal {
      t.printStackTrace()
      println(message)
    }

    def trace(t: Throwable)(message: => String): ZIO[Any, Error, Unit] = UIO.effectTotal {
      t.printStackTrace()
      println(message)
    }
  }
}
