name := "play-cms"

version := "1.0-SNAPSHOT"

//lazy val templates = (project in file("play-cms-templates"))

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
//  .dependsOn(templates)

scalaVersion := "2.11.8"

libraryDependencies += jdbc
libraryDependencies += cache
libraryDependencies += ws
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
libraryDependencies += "uk.co.huntersix" %% "play-cms-templates" % "1.1-SNAPSHOT"