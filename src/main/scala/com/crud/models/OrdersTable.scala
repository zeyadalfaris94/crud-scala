package com.crud.models

import slick.lifted.{MappedProjection, Rep, Tag}
import slick.jdbc.H2Profile.api._

import java.time.ZonedDateTime
import java.util.UUID

final case class OrdersEntity(
    id: UUID,
    name: String,
    price: Double,
    quantity: Int,
    status: String,
    createdAt: ZonedDateTime,
    updatedAt: ZonedDateTime
)

object OrdersTable {
  type Row = (UUID, String, Double, Int, String, ZonedDateTime, ZonedDateTime)

  class OrdersTable(tag: Tag) extends Table[OrdersEntity](tag, "orders") {
    def id: Rep[UUID] = column[UUID]("id", O.PrimaryKey)
    def name: Rep[String] = column[String]("name")
    def price: Rep[Double] = column[Double]("price")
    def quantity: Rep[Int] = column[Int]("quantity")
    def status: Rep[String] = column[String]("status")
    def createdAt: Rep[ZonedDateTime] = column[ZonedDateTime]("created_at")
    def updatedAt: Rep[ZonedDateTime] = column[ZonedDateTime]("updated_at")

    override def * =
      (
        id,
        name,
        price,
        quantity,
        status,
        createdAt,
        updatedAt
      ) <> (rowToOrder, orderToRow)
  }

  val ordersTable: Query[OrdersTable, OrdersEntity, Seq] =
    TableQuery[OrdersTable]

  private def rowToOrder(row: Row): OrdersEntity = {
    val (id, name, price, quantity, status, createdAt, updatedAt) = row
    OrdersEntity(
      id,
      name,
      price,
      quantity,
      status,
      createdAt,
      updatedAt
    )
  }

  private def orderToRow(order: OrdersEntity): Option[Row] = Some(
    order.id,
    order.name,
    order.price,
    order.quantity,
    order.status,
    order.createdAt,
    order.updatedAt
  )

}
