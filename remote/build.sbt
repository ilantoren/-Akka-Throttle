name := "throttleRemote"

version := "1.0"

scalaVersion := "2.11.1"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-remote" % "2.3.5",
  "org.specs2" % "specs2_2.10" % "2.2.3",
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.9" % "test->default",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)

