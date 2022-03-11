name := "core"

version := "0.1"

scalaVersion := "2.13.8"

idePackagePrefix := Some("com.tapestry")

// Test
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"
libraryDependencies += "org.scalamock" %% "scalamock" % "5.1.0" % Test
libraryDependencies += "org.mockito" % "mockito-scala_2.13" % "1.17.5"
libraryDependencies += "org.mockito" % "mockito-scala-scalatest_2.13" % "1.17.5"

//Tapestry core
libraryDependencies += "org.reflections" % "reflections" % "0.10.2"
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.13.8"
libraryDependencies += "com.google.guava" % "guava" % "31.0.1-jre"
libraryDependencies += "org.json4s" %% "json4s-native" % "4.0.4"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "4.0.4"

//Tapestry local
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.13.2"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.13.2"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.13.2"
libraryDependencies += "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.13.2"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.2"

//Search
libraryDependencies += "com.nrinaudo" % "kantan.csv_2.13" % "0.6.2"
libraryDependencies += "com.nrinaudo" % "kantan.csv-generic_2.13" % "0.6.2"
libraryDependencies += "com.chuusai" % "shapeless_2.13" % "2.3.7"
libraryDependencies += "org.apache.lucene" % "lucene-core" % "8.11.1"
libraryDependencies += "org.apache.lucene" % "lucene-analyzers-common" % "8.11.1"
libraryDependencies += "org.apache.lucene" % "lucene-queryparser" % "8.11.1"

//Search Deep Learning
libraryDependencies += "org.deeplearning4j" % "deeplearning4j-core" % "1.0.0-M1"
libraryDependencies += "org.deeplearning4j" % "deeplearning4j-nlp" % "1.0.0-M1"
libraryDependencies += "org.nd4j" % "nd4j-native" % "1.0.0-M1"
libraryDependencies += "org.nd4j" % "nd4j-native-platform" % "1.0.0-M1"

//Iot is T
libraryDependencies += "dev.profunktor" %% "redis4cats-effects" % "1.1.1"
libraryDependencies += "dev.profunktor" %% "redis4cats-streams" % "1.1.1"
libraryDependencies += "io.github.kirill5k" %% "mongo4cats-core" % "0.4.6"

fork in Test := true
(fullClasspath in Test) := (fullClasspath in Test).value ++ Seq(Attributed.blank((resourceDirectory in Test).value))

scalacOptions += "-deprecation"

lazy val local = (project in file("local")).settings(
  Compile / run / mainClass := Some("com.tapestry.textiles.local.LocalMain")
)
