enablePlugins(OssLibPlugin)

organization := "com.thenewmotion"

description := "Scala Google geocode api client"

scalaVersion := tnm.ScalaVersion.prev
crossScalaVersions := Seq(tnm.ScalaVersion.prev, tnm.ScalaVersion.aged)

libraryDependencies ++= Seq(
  "org.dispatchhttp" %% "dispatch-core" % "1.0.2",
  "io.spray" %% "spray-json" % "1.3.5",
  "org.specs2" %% "specs2-core" % "4.8.1" % "test"
)

