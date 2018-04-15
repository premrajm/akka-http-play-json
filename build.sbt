lazy val akkaHttpVersion = "10.0.11"
lazy val akkaVersion    = "2.5.11"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.github.premrajm",
      scalaVersion    := "2.11.8"
    )),
    name := "Akka HTTP",
    resolvers += Resolver.bintrayRepo("hseeberger", "maven"),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "de.heikoseeberger" %% "akka-http-play-json"  % "1.20.0",
      "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
      "com.beachape" %% "enumeratum" % "1.5.13",
      "com.beachape" %% "enumeratum-play-json" % "1.5.14",

      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.1"         % Test
    )
  )
