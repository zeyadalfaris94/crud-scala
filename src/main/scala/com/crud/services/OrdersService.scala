package com.crud.services

import com.crud.models.Order

import scala.annotation.unused

trait OrdersService[F[_]] {
  def getOrders: F[Seq[Order]]
  def getOrder(id: Int): F[Option[Order]]
  def createOrder(): F[Unit]
  def updateOrder(id: Int): F[Unit]
  def deleteOrder(id: Int): F[Unit]
}
