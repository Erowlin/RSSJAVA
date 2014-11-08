name := """rss-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "be.objectify"  %% "deadbolt-java"     % "2.3.0-RC1",
  "postgresql"    %  "postgresql"        % "9.1-901-1.jdbc4",
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)

resolvers ++= Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Apache" at "http://repo1.maven.org/maven2/",
  "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/",
  Resolver.url("Objectify Play Repository", url("http://schaloner.github.io/releases/"))(Resolver.ivyStylePatterns)
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.ning" % "async-http-client" % "1.6.4",
  "org.jdom" % "jdom" % "2.0.2",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.webjars" % "angularjs" % "1.2.26",
  "org.webjars" % "requirejs" % "2.1.11-1"
)
