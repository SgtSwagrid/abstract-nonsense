import IdeSettings.packagePrefix
import sbt._
import sbt.Keys._

ThisBuild / scalaVersion := "3.9.0"

scalacOptions ++= Seq(
  "-explain",
  "-explain-types",
  "-explain-cyclic",
)

lazy val `abstract-nonsense` = (project in file(".")).settings(
  packagePrefix := "io.github.sgtswagrid.nonsense",
)
