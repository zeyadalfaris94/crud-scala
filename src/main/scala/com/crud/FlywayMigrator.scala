package com.crud

import cats.effect.Sync
import cats.syntax.flatMap._
import cats.syntax.functor._
import com.crud.configs.DatabaseConfig
import org.flywaydb.core.Flyway

object FlywayMigrator {
  final def migrate[F[_]: Sync](config: DatabaseConfig): F[Int] =
    for {
      flyway <- loadFlyway(config)
      applied <- Sync[F].delay(flyway.migrate())
    } yield applied.migrationsExecuted

  private def loadFlyway[F[_]: Sync](config: DatabaseConfig): F[Flyway] =
    Sync[F].delay {
      Flyway.configure
        .locations(config.migrationConfig.migrationFilesLocation)
        .dataSource(
          config.url,
          config.migrationConfig.user,
          config.migrationConfig.password
        )
        .load()

    }

}
