ThisBuild / description := "A collection of category-theoretic base traits. "

ThisBuild / homepage :=
  Some(url("https://alecdorrington.com/abstract-nonsense"))

ThisBuild / organization         := "com.alecdorrington"
ThisBuild / organizationName     := "Alec Dorrington"
ThisBuild / organizationHomepage := Some(url("https://alecdorrington.com"))

ThisBuild / versionScheme := Some("strict")

ThisBuild / licenses :=
  List("MIT" -> url("https://opensource.org/licenses/MIT"))

ThisBuild / developers := List(Developer(
  id = "SgtSwagrid",
  name = "Alec Dorrington",
  email = "alecdorrington@gmail.com",
  url = url("https://github.com/SgtSwagrid"),
))
