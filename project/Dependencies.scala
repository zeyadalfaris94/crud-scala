import sbt._

object Dependencies {

  private object Version {
    val catsEffect = "3.4.8"
    val http4s = "1.0.0-M33"
    val circe = "0.14.5"
    val scalaTest = "3.2.15"
    val doobie = "1.0.0-RC1"
    val flyway = "9.2.0"
    val testContainerPostgresql = "1.17.6"
    val testContainerScala = "0.40.12"
    val scalaCheck = "1.17.0"
    val caseInsensitive = "1.3.0"
    val pureConfig = "0.17.4"
    val apacheCommonsValidator = "1.7"
    val hikari = "5.0.1"
  }

  lazy val dependencies: Seq[ModuleID] = Seq(
    "com.github.pureconfig" %% "pureconfig" % Version.pureConfig,
    "org.typelevel" %% "cats-effect" % Version.catsEffect,
    "org.http4s" %% "http4s-dsl" % Version.http4s,
    "org.http4s" %% "http4s-blaze-server" % Version.http4s,
    "org.http4s" %% "http4s-core" % Version.http4s,
    "io.circe" %% "circe-literal" % Version.circe,
    "io.circe" %% "circe-parser" % Version.circe,
    "io.circe" %% "circe-generic" % Version.circe,
    "org.scalacheck" %% "scalacheck" % Version.scalaCheck,
    "org.typelevel" %% "case-insensitive" % Version.caseInsensitive,
    "commons-validator" % "commons-validator" % Version.apacheCommonsValidator,
    "com.zaxxer" % "HikariCP" % Version.hikari,
    "com.typesafe.slick" %% "slick" % "3.4.1",
    "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
    "com.github.tminglei" %% "slick-pg" % "0.21.1",
    "com.github.tminglei" %% "slick-pg_circe-json" % "0.21.1",
    "org.slf4j" % "slf4j-nop" % "2.0.5",
    "org.postgresql" % "postgresql" % "42.5.4",
    "org.flywaydb" % "flyway-core" % "9.16.0"
  )

  lazy val testDependencies: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % Version.scalaTest,
    "org.testcontainers" % "postgresql" % Version.testContainerPostgresql,
    "com.dimafeng" %% "testcontainers-scala" % Version.testContainerScala
  ).map(_ % (IntegrationTest, Test).productIterator.mkString(","))
}
