package com.crud.services

import com.crud.models.Order
import slick.jdbc.H2Profile.api._

class OrdersServiceImpl[F[_]](db: Database) extends OrdersService[F] {
  override def getOrders: F[Seq[Order]] = ???

  override def getOrder(id: Int): F[Option[Order]] = ???

  override def createOrder(): F[Unit] = ???

  override def updateOrder(id: Int): F[Unit] = ???

  override def deleteOrder(id: Int): F[Unit] = ???
}
