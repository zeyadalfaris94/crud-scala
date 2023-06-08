package com.crud.models

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

import java.util.UUID

final case class Order(
    id: UUID,
    name: String,
    price: Double,
    quantity: Int,
    status: String
)

object Order {
  implicit val encoder: Encoder[Order] =
    deriveEncoder[Order]
  implicit val decoder: Decoder[Order] =
    deriveDecoder[Order]

}
