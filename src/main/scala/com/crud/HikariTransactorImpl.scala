package com.crud

import cats.effect.{Async, Resource}
import com.crud.configs.DatabaseConfig
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts

object HikariTransactorImpl {
  def apply[F[_]: Async](
      databaseConfig: DatabaseConfig
  ): Resource[F, HikariTransactor[F]] =
    for {
      connectionPool <- ExecutionContexts.fixedThreadPool[F](
        databaseConfig.connectionPoolSize.toInt
      )
      xa <- HikariTransactor.newHikariTransactor[F](
        databaseConfig.driverClassName,
        databaseConfig.url,
        databaseConfig.user,
        databaseConfig.password,
        connectionPool
      )
      _ <- Resource.eval(
        xa.configure(ds => Async[F].delay(ds.setConnectionTimeout(30000)))
      )
    } yield xa
}
