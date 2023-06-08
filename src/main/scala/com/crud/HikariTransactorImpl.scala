package com.crud

import cats.effect.{Async, Resource}
import com.crud.configs.DatabaseConfig
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import slick.jdbc.H2Profile.api._

object HikariTransactorImpl {
  def apply[F[_]: Async](
      databaseConfig: DatabaseConfig
  ): Resource[F, Database] =
    Resource
      .make(
        Async[F].delay(createHikariDataSource(databaseConfig))
      )(hikariDataSource => Async[F].delay(hikariDataSource.close()))
      .map { hikariDataSource =>
        Database.forDataSource(hikariDataSource, None)
      }

  private def createHikariDataSource(
      databaseConfig: DatabaseConfig
  ): HikariDataSource = {
    val hikariConfig = new HikariConfig()
    hikariConfig.setDriverClassName(databaseConfig.driverClassName)
    hikariConfig.setJdbcUrl(databaseConfig.url)
    hikariConfig.setUsername(databaseConfig.user)
    hikariConfig.setPassword(databaseConfig.password)
    hikariConfig.setMaximumPoolSize(databaseConfig.connectionPoolSize.toInt)
    new HikariDataSource(hikariConfig)
  }

}
