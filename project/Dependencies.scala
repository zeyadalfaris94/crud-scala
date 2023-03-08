import sbt._

object Dependencies {

  private object Version {
    val catsEffect = "3.3.14"
    val http4s = "1.0.0-M33"
    val circe = "0.14.1"
    val scalaTest = "3.2.12"
    val doobie = "1.0.0-RC1"
    val flyway = "9.2.0"
    val testContainerPostgresql = "1.17.3"
    val testContainerScala = "0.40.10"
    val scalaCheck = "1.16.0"
    val caseInsensitive = "1.3.0"
    val pureConfig = "0.17.1"
    val circeDerivation = "0.13.0-M5"
    val apacheCommonsValidator = "1.7"
    val hikari = "5.0.1"
  }

  lazy val dependencies: Seq[ModuleID] = Seq(
    "com.github.pureconfig" %% "pureconfig" % Version.pureConfig,
    "org.typelevel" %% "cats-effect" % Version.catsEffect,
    "org.http4s" %% "http4s-dsl" % Version.http4s,
    "org.http4s" %% "http4s-blaze-server" % Version.http4s,
    "org.http4s" %% "http4s-core" % Version.http4s,
    "org.http4s" %% "http4s-circe" % Version.http4s,
    "io.circe" %% "circe-generic-extras" % Version.circe,
    "io.circe" %% "circe-derivation" % Version.circeDerivation,
    "org.tpolecat" %% "doobie-postgres" % Version.doobie,
    "org.tpolecat" %% "doobie-core" % Version.doobie,
    "org.tpolecat" %% "doobie-hikari" % Version.doobie,
    "org.flywaydb" % "flyway-core" % Version.flyway,
    "org.tpolecat" %% "doobie-postgres-circe" % Version.doobie,
    "org.scalacheck" %% "scalacheck" % Version.scalaCheck,
    "org.typelevel" %% "case-insensitive" % Version.caseInsensitive,
    "commons-validator" % "commons-validator" % Version.apacheCommonsValidator,
    "com.zaxxer" % "HikariCP" % Version.hikari
  )

  lazy val testDependencies: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % Version.scalaTest,
    "org.testcontainers" % "postgresql" % Version.testContainerPostgresql,
    "com.dimafeng" %% "testcontainers-scala" % Version.testContainerScala
  ).map(_ % (IntegrationTest, Test).productIterator.mkString(","))
}
