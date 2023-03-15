package com.crud.controllers

import cats.effect.Async
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

class HealthRoute[F[_]: Async] extends Http4sDsl[F] {
  val endpoints: HttpRoutes[F] = HttpRoutes.of[F] { case GET -> Root =>
    println("HealthRoute")
    Ok("alive")
  }
}

object HealthRoute {
  def apply[F[_]: Async](): HealthRoute[F] = new HealthRoute[F]()
}
