package com.crud.controllers

import cats.effect.Async
import com.crud.services.OrdersService
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

class OrdersRoute[F[_]: Async](ordersService: OrdersService[F])
    extends Http4sDsl[F] {
  val endpoints: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root => {
      println("OrdersRoute")
      Ok("alive")
    }
  }
}

object OrdersRoute {
  def apply[F[_]: Async](ordersService: OrdersService[F]): OrdersRoute[F] =
    new OrdersRoute[F](ordersService)
}
