package com.crud.models

import java.time.ZonedDateTime
import java.util.UUID

case class User(
    id: UUID,
    firstName: String,
    lastName: String,
    userName: String,
    email: String,
    birthdate: ZonedDateTime
)
