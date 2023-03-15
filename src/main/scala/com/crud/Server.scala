package com.crud

import cats.effect.Async
import org.http4s.HttpApp
import org.http4s.blaze.server.BlazeServerBuilder

import scala.concurrent.ExecutionContext

object Server {

  def run[F[_]: Async](
      httpApp: HttpApp[F],
      executionContext: ExecutionContext
  ): F[Unit] =
    BlazeServerBuilder[F]
      .withExecutionContext(executionContext)
      .bindHttp(8080, "0.0.0.0")
      .withHttpApp(httpApp)
      .serve
      .compile
      .drain
}
