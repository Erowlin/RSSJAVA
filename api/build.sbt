name := """rss-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.ning" % "async-http-client" % "1.6.4",
  "org.jdom" % "jdom" % "2.0.2"
)
