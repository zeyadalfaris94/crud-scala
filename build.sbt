val rootProject = project
  .in(file("."))
  .settings(
    name := "crud-scala",
    version := "0.1",
    scalaVersion := "2.13.8",
    libraryDependencies ++= Dependencies.dependencies,
    assembly / assemblyJarName := "crud-scala.jar"
  )
