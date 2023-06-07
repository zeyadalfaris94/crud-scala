package com.crud

import com.crud.models.Order
import com.crud.services.OrdersService

class OrdersServiceImpl[F[_]] extends OrdersService[F] {
  override def getOrders(): F[List[Order]] = ???

  override def getOrder(id: Int): F[Option[Order]] = ???

  override def createOrder(): F[Unit] = ???

  override def updateOrder(id: Int): F[Unit] = ???

  override def deleteOrder(id: Int): F[Unit] = ???
}
