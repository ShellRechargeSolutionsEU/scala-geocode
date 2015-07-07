val geocode =
  project.in(file("."))
  .enablePlugins(OssLibPlugin)
  .settings(
    organization := "com.thenewmotion",
    description := "Scala Google geocode api client",
    libraryDependencies ++= Seq(
      "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
      "io.spray" %% "spray-json" % "1.2.6",
      "org.specs2" %% "specs2-core" % "3.6.1" % "test"
    )
  )

