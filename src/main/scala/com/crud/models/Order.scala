package com.crud.models

import java.time.ZonedDateTime
import java.util.UUID

final case class Order(
    id: UUID,
    name: String,
    price: Double,
    quantity: Int,
    status: String
)
