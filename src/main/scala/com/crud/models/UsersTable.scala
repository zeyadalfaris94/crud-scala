package com.crud.models

import slick.lifted.{ProvenShape, Tag}
import slick.jdbc.H2Profile.api._

import java.time.ZonedDateTime
import java.util.UUID

case class UserEntity(
    id: UUID,
    firstName: String,
    lastName: String,
    userName: String,
    email: String,
    birthdate: ZonedDateTime,
    createdAt: ZonedDateTime,
    updatedAt: ZonedDateTime
)
object UsersTable {

  type Row = (
      UUID,
      String,
      String,
      String,
      String,
      ZonedDateTime,
      ZonedDateTime,
      ZonedDateTime
  )

  class UsersTable(tag: Tag) extends Table[UserEntity](tag, "users") {
    def id: Rep[UUID] = column[UUID]("id", O.PrimaryKey)

    def firstName: Rep[String] = column[String]("first_name")

    def lastName: Rep[String] = column[String]("last_name")

    def userName: Rep[String] = column[String]("user_name")

    def email: Rep[String] = column[String]("email")

    def birthdate: Rep[ZonedDateTime] = column[ZonedDateTime]("birthdate")

    def createdAt: Rep[ZonedDateTime] = column[ZonedDateTime]("created_at")

    def updatedAt: Rep[ZonedDateTime] = column[ZonedDateTime]("updated_at")

    override def * = (
      id,
      firstName,
      lastName,
      userName,
      email,
      birthdate,
      createdAt,
      updatedAt
    ) <> (rowToUser, userToRow)
  }

  val usersTable: Query[UsersTable, UserEntity, Seq] = TableQuery[UsersTable]

  private def rowToUser(row: Row): UserEntity = {
    val (
      id,
      firstName,
      lastName,
      userName,
      email,
      birthdate,
      createdAt,
      updatedAt
    ) = row
    UserEntity(
      id,
      firstName,
      lastName,
      userName,
      email,
      birthdate,
      createdAt,
      updatedAt
    )
  }

  private def userToRow(user: UserEntity): Option[Row] = Some(
    user.id,
    user.firstName,
    user.lastName,
    user.userName,
    user.email,
    user.birthdate,
    user.createdAt,
    user.updatedAt
  )
}
