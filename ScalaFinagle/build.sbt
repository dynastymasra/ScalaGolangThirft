name := "ScalaFinagle"
organization := "com.squarecode.axia"
description := "Sample app with scala with thrift use finagle and scrooge"
licenses := Seq("MIT License" -> url("https://opensource.org/licenses/MIT"))
version := "0.0.1"

scalaVersion := "2.11.8"

sbtPlugin := true

resolvers ++= Seq(
  "Typesafe repository snapshots"    at "http://repo.typesafe.com/typesafe/snapshots/",
  "Typesafe repository releases"     at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype repo"                    at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases"                at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots"               at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype staging"                 at "http://oss.sonatype.org/content/repositories/staging",
  "Java.net Maven2 Repository"       at "http://download.java.net/maven/2/",
  "Twitter Repository"               at "http://maven.twttr.com",
  "Websudos releases"                at "https://dl.bintray.com/websudos/oss-releases/"
)

lazy val hello = taskKey[Unit]("Prints 'Hello World'")
hello := println("hello world!")

lazy val versions = new {
  val finagle = "6.34.0"
  val scrooge = "4.6.0"
  val libthrift = "0.9.3"
  val scalatest = "2.2.4"
  val junit = "4.12"
  val config = "1.3.0"
  val scalalogging = "3.1.0"
  val logback = "1.1.6"
  val phantom = "1.22.0"
  val utiltest = "0.9.11"
}

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-core" % versions.finagle,
  "com.twitter" %% "finagle-thrift" % versions.finagle,
  "com.twitter" %% "finagle-thriftmux" % versions.finagle,
  "com.twitter" %% "scrooge-core" % versions.scrooge,
  "org.apache.thrift" % "libthrift" % versions.libthrift,
  "com.typesafe" % "config" % versions.config,
  "com.typesafe.scala-logging" %% "scala-logging" % versions.scalalogging,
  "ch.qos.logback" % "logback-classic" % versions.logback,
  "com.websudos"  %% "phantom-dsl" % versions.phantom,
  "com.websudos" %% "phantom-thrift" % versions.phantom,

  "junit" % "junit" % versions.junit % "test",
  "org.scalatest" %% "scalatest" % versions.scalatest % "test",
  "com.websudos" %% "util-testing" % versions.utiltest % "test, provided"
)

parallelExecution in Test := false
coverageEnabled in Test := true
coverageMinimum := 70
coverageFailOnMinimum := false
coverageExcludedPackages := "com.twitter.finagle.*"

crossPaths := false
unmanagedSourceDirectories in Compile += baseDirectory.value / "target" / "scala-2.11" / "src_managed"
unmanagedSourceDirectories in Test += baseDirectory.value / "target" / "scala-2.11" / "src_managed"