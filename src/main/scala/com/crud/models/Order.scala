package com.crud.models

case class Order(
    id: Int,
    name: String,
    price: Double,
    quantity: Int,
    status: String,
    createdAt: String,
    updatedAt: String
)
