package com.crud.controllers

import cats.effect.Async
import cats.Parallel
import org.http4s.dsl.Http4sDsl
import cats.implicits._
import com.crud.services.OrdersService
import org.http4s.HttpRoutes

class OrdersRoute[F[_]: Async: Parallel](ordersService: OrdersService[F])
    extends Http4sDsl[F] {

  val endpoints: HttpRoutes[F] = HttpRoutes
    .of[F] { case GET -> Root =>
      ordersService.getOrders.flatMap(Ok(_))

    }

}

object OrdersRoute {
  def apply[F[_]: Async: Parallel](
      ordersService: OrdersService[F]
  ): OrdersRoute[F] =
    new OrdersRoute[F](ordersService)
}
