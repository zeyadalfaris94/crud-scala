package com.crud

import cats.effect.{IO, Sync}
import cats.implicits._
import cats.effect.IOApp.Simple
import com.crud.configs.CrudConfig
import com.crud.controllers.{HealthRoute, OrdersRoute}
import com.crud.services.{OrdersService, OrdersServiceImpl}
import com.typesafe.config.{Config, ConfigFactory}
import org.http4s.server.Router
import pureconfig._
import pureconfig.generic.auto._
import slick.jdbc.H2Profile.api._

object Main extends Simple {

  override def run: IO[Unit] =
    loadConfig[IO].flatMap(config =>
      FlywayMigrator.migrate[IO](config.database) >> HikariTransactorImpl[IO](
        config.database
      ).use(db => program(db, config))
    )

  private def program(db: Database, config: CrudConfig): IO[Unit] = {
    (for {
      _ <- IO(println("Starting server..."))
      ordersService <- IO.pure(new OrdersServiceImpl[IO](db))
      routes <- IO.pure(
        Router(
          "/health" -> HealthRoute[IO]().endpoints,
          "/orders" -> OrdersRoute[IO](ordersService).endpoints
        ).orNotFound
      )
      _ <- Server.run[IO](routes, runtime.compute)
    } yield ()).handleErrorWith { ex =>
      IO(println(s"Error: $ex")) >> IO.raiseError(ex)
    }
  }

  private def loadConfig[F[_]: Sync]: F[CrudConfig] = {
    val config: Config = ConfigFactory.load()
    ConfigSource.fromConfig(config).at("crud").load[CrudConfig] match {
      case Left(ex)   => throw new RuntimeException(ex.toList.mkString(", "))
      case Right(cfg) => Sync[F].delay(cfg)
    }
  }
}
