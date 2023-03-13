package com.crud

import cats.effect.{IO, Sync}
import cats.implicits._
import cats.effect.IOApp.Simple
import com.crud.configs.CrudConfig
import com.typesafe.config.{Config, ConfigFactory}
import doobie.hikari.HikariTransactor
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
    IO(println("Hello, world!"))

  private def loadConfig[F[_]: Sync]: F[CrudConfig] = {
    val config: Config = ConfigFactory.load()
    ConfigSource.fromConfig(config).at("crud").load[CrudConfig] match {
      case Left(ex)   => throw new RuntimeException(ex.toList.mkString(", "))
      case Right(cfg) => Sync[F].delay(cfg)
    }
  }
}
