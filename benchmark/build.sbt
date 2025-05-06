name := "eks-spark-benchmark"

version := "1.0"

scalaVersion := "2.12.18"
val sparkVersion = "3.5.4"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

// Dependencies required for this project
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "com.databricks" %% "spark-sql-perf" % "0.5.1-SNAPSHOT",
  // JSON serialization
  "org.json4s" %% "json4s-native" % "3.6.7",
)

// Remove stub classes
assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", "spark", "unused", "UnusedStubClass.class") => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

// Exclude the Scala runtime jars
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

resolvers := Seq(
  Resolver.mavenLocal
)

