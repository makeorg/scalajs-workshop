/*
 * Copyright 2018 Make.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

name := "back-application"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.5",
  "com.typesafe.akka" %% "akka-actor" % "2.5.16",
  "com.typesafe.akka" %% "akka-stream" % "2.5.16",
  "de.knutwalker" %% "akka-http-circe" % "3.5.0",
  "io.circe" %% "circe-core" % "0.10.0"
)

// Docker section
enablePlugins(UniversalPlugin)
enablePlugins(JavaServerAppPackaging)
enablePlugins(DockerPlugin)

dockerBaseImage := "makeorg/centos-java:latest"
// Open 4k for jmx and 9k for http
dockerExposedPorts := Seq(4000, 9000)
daemonUser in Docker := "user"
packageName in Docker := "scalajs-workshop-backend"

publishLocal := {
  (packageBin in Universal).value
  (publishLocal in Docker).value
}

publish := {
  (packageBin in Universal).value
  (publish in Docker).value
}