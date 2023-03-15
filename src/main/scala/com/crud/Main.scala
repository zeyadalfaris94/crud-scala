package com.crud

import cats.effect.{IO, Sync}
import cats.implicits._
import cats.effect.IOApp.Simple
import com.crud.configs.CrudConfig
import com.crud.controllers.HealthRoute
import com.typesafe.config.{Config, ConfigFactory}
import doobie.hikari.HikariTransactor
import org.http4s.server.Router
import pureconfig._
import pureconfig.generic.auto._

object Main extends Simple {

  override def run: IO[Unit] =
    loadConfig[IO].flatMap(config =>
      FlywayMigrator.migrate[IO](config.database) >> HikariTransactorImpl[IO](
        config.database
      ).use(xa => program(xa, config))
    )

  private def program(xa: HikariTransactor[IO], config: CrudConfig): IO[Unit] =
    (for {
      _ <- IO(println("Starting server..."))
      _ <- IO(println(s"Config: $config"))
      routes <- IO.pure(
        Router(
          "/health" -> HealthRoute[IO]().endpoints
        ).orNotFound
      )
      _ <- Server.run[IO](routes, runtime.compute)
    } yield ()).handleErrorWith { ex =>
      IO(println(s"Error: $ex")) >> IO.raiseError(ex)
    }

  private def loadConfig[F[_]: Sync]: F[CrudConfig] = {
    val config: Config = ConfigFactory.load()
    ConfigSource.fromConfig(config).at("crud").load[CrudConfig] match {
      case Left(ex)   => throw new RuntimeException(ex.toList.mkString(", "))
      case Right(cfg) => Sync[F].delay(cfg)
    }
  }
}
