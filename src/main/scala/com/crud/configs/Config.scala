package com.crud.configs

final case class CrudConfig(database: DatabaseConfig)

final case class DatabaseConfig(
    host: String,
    driverClassName: String,
    databaseName: String,
    user: String,
    password: String,
    ssl: String,
    sslMode: String,
    connectionPoolSize: String,
    migrationConfig: MigrationConfig
) {
  val url: String = {
    val params =
      s"""ssl=${ssl}&sslmode=$sslMode&sslfactory=org.postgresql.ssl.NonValidatingFactory"""
    val jdbcUrl =
      s"""jdbc:postgresql://${host}/${databaseName}"""
    jdbcUrl
  }
}

final case class MigrationConfig(
    migrationFilesLocation: String,
    user: String,
    password: String
)
