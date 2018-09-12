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

import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

val sharedSettings = Seq(
  organization := "org.make.scalajs-workshop",
  scalaVersion := "2.12.6",
  scalacOptions ++= Seq(
    "-Yrangepos",
    "-Xlint",
    "-deprecation",
    "-Xfatal-warnings",
    "-feature",
    "-encoding",
    "UTF-8",
    "-unchecked",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Xfuture",
    "-Ywarn-unused-import",
    "-language:_",
    "-Ycache-plugin-class-loader:last-modified",
    "-Ycache-macro-class-loader:last-modified",
    "-Ybackend-parallelism",
    "5"
  )
)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .settings(sharedSettings)
  .jsSettings(sharedSettings)
  .jvmSettings(sharedSettings)

lazy val js = project
  .in(file("js"))
  .dependsOn(shared.js)
  .enablePlugins(ScalaJSPlugin)
  .settings(sharedSettings)

lazy val jvm = project
  .in(file("jvm"))
  .dependsOn(shared.jvm)
  .settings(sharedSettings)
