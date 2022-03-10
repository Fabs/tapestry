name := "core"

version := "0.1"

scalaVersion := "2.13.8"

idePackagePrefix := Some("com.tapestry")

//Tapestry core
libraryDependencies += "org.reflections" % "reflections" % "0.10.2"
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.13.8"
libraryDependencies += "com.google.guava" % "guava" % "31.0.1-jre"
libraryDependencies += "org.json4s" %% "json4s-native" % "4.0.4"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "4.0.4"

//Tapestry local

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

//IoT


lazy val sample = (project in file("sample")).settings(
  Compile / run / mainClass := Some("com.tapestry.textiles.sample.SampleMain")
)

lazy val local = (project in file("local")).settings(
  Compile / run / mainClass := Some("com.tapestry.textiles.local.LocalMain")
)
