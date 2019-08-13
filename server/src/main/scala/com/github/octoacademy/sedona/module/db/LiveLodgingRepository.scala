package com.github.octoacademy.sedona.module.db

import com.github.octoacademy.sedona.model.database.Lodging
import com.github.octoacademy.sedona.model.{DBError, Error, NotFoundError, UnexpectedError}
import com.typesafe.config.Config
import io.getquill.{H2JdbcContext, SnakeCase}
import zio.ZIO

trait LiveLodgingRepository extends LodgingRepository {
  val config: Config

  override val repository: LodgingRepository.Service = new LodgingRepository.Service {
    lazy val ctx = new H2JdbcContext(SnakeCase, config)

    import ctx._

    def get(id: Long): ZIO[Any, Error, Lodging] = {
      (for {
        list <- zio.IO.effect(ctx.run(query[Lodging].filter(_.id == lift(id))))
        user <- list match {
          case Nil => ZIO.fail(NotFoundError(s"Not found a user by id = $id"))
          case s :: _ => ZIO.succeed(s)
        }
      } yield {
        user
      }).mapError {
        case e: Exception => DBError(e)
        case t: Throwable => UnexpectedError(t)
      }
    }

    def create(lodging: Lodging): ZIO[Any, Error, Lodging] = {
      zio.IO.effect(ctx.run(query[Lodging].insert(lift(lodging)))).mapError {
        case e: Exception => DBError(e)
        case t: Throwable => UnexpectedError(t)
      } *> get(lodging.id)
    }

    def delete(id: Long): ZIO[Any, Error, Unit] = {
      zio.IO
        .effect(ctx.run(query[Lodging].filter(_.id == lift(id)).delete))
        .mapError {
          case e: Exception => DBError(e)
          case t: Throwable => UnexpectedError(t)
        }
        .map(_ => ())
    }
  }
}
