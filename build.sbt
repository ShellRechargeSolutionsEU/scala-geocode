enablePlugins(OssLibPlugin)

organization := "com.thenewmotion"

description := "Scala Google geocode api client"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.13.1",
  "io.spray" %% "spray-json" % "1.3.3",
  "org.specs2" %% "specs2-core" % "3.9.1" % "test"
)

