import IdeSettings.packagePrefix
import sbt._
import sbt.Keys._

// Basic details about this library.
ThisBuild / name        := "Abstract Nonsense"
ThisBuild / description := "Base traits for algebraic structures."

ThisBuild / homepage :=
  Some(url("https://github.com/SgtSwagrid/abstract-nonsense"))

// The organisation who maintains this library.
ThisBuild / organization         := "io.github.sgtswagrid"
ThisBuild / organizationName     := "Alec Dorrington"
ThisBuild / organizationHomepage := Some(url("https://github.com/SgtSwagrid"))

// The URL for the GitHub repository of this library.
ThisBuild / scmInfo := Some(ScmInfo(
  url("https://github.com/SgtSwagrid/abstract-nonsense"),
  "scm:git@github.com:SgtSwagrid/abstract-nonsense.git",
))

// The licence under which this library is released.
ThisBuild / licenses :=
  List("MIT License" -> url("https://opensource.org/licenses/MIT"))

ThisBuild / developers := List(Developer(
  id = "SgtSwagrid",
  name = "Alec Dorrington",
  email = "alecdorrington@gmail.com",
  url = url("https://github.com/SgtSwagrid"),
))

ThisBuild / scalaVersion := "3.8.3"

// Target the Sonatype Central Portal (https://central.sonatype.com).
// Set SONATYPE_USERNAME and SONATYPE_PASSWORD as GitHub secrets.
ThisBuild / sonatypeCredentialHost := "central.sonatype.com"
ThisBuild / publishMavenStyle      := true
Global / excludeLintKeys ++= Set(name, publishMavenStyle)

scalacOptions ++= Seq(
  "-explain",
  "-explain-types",
  "-explain-cyclic",
)

lazy val `abstract-nonsense` = (project in file(".")).settings(
  packagePrefix := "io.github.sgtswagrid.nonsense",
  scalacOptions ++= Seq(
    "-language:experimental.subCases",
    "-language:experimental.relaxedLambdaSyntax",
    "-language:experimental.multiSpreads",
    "-language:experimental.strictEqualityPatternMatching",
    "-language:experimental.erasedDefinitions",
    "-Xmax-inlines",
    "64",
  ),
)
