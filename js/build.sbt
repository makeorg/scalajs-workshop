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

name := "front-application"

enablePlugins(ScalaJSBundlerPlugin)

libraryDependencies ++= Seq(
  "com.github.japgolly.scalajs-react" %%% "core" % "1.2.3",
  "com.github.japgolly.scalacss" %%% "core" % "0.5.3",
  "com.github.japgolly.scalacss" %%% "ext-react" % "0.5.3",
  "com.github.japgolly.scalajs-react" %%% "extra" % "1.3.1",
  "io.circe" %%% "circe-parser" % "0.10.0",

  "com.github.japgolly.scalajs-react" %%% "test" % "1.3.1" % Test,
  "org.scalatest" %%% "scalatest" % "3.0.5" % Test,
  "org.scalamock" %%% "scalamock" % "4.1.0" % Test
)

npmDevDependencies in Compile ++= Seq(
  "ajv" -> "6.5.3",
  "clean-webpack-plugin" -> "0.1.19",
  "css-loader" -> "1.0.0",
  "extract-text-webpack-plugin" -> "v4.0.0-beta.0",
  "file-loader" -> "2.0.0",
  "hard-source-webpack-plugin" -> "0.12.0",
  "html-webpack-plugin" -> "3.2.0",
  "style-loader" -> "0.23.0",
  "webpack-dev-server" -> "3.1.8",
  "uglifyjs-webpack-plugin" -> "1.3.0",
  "webpack" -> "4.18.1",
  "react-dom" -> "16.5.1",
  "jsdom" -> "12.2.0"
)

npmDependencies in Compile ++= Seq(
  "react" -> "16.5.1",
  "react-dom" -> "16.5.1",
  "react-i18nify" -> "1.11.14"
)

requiresDOM in Test := true

Test / emitSourceMaps := true

scalaJSUseMainModuleInitializer := true

version in webpack := "4.18.1"
version in startWebpackDevServer := "3.1.8"

npmResolutions in Compile := {
  (npmDependencies in Compile).value.toMap ++ (npmDevDependencies in Compile).value.toMap
}

webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack-dev-config.js")
webpackConfigFile in fullOptJS := Some(baseDirectory.value / "webpack-prod-config.js")

webpackDevServerExtraArgs := Seq("--host", "0.0.0.0", "--hot", "--watch-content-base")
webpackDevServerPort := 9009

webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly("scalaIO")
webpackBundlingMode in fullOptJS := BundlingMode.Application

useYarn := true



val prepareAssets = taskKey[Unit]("prepareAssets")

prepareAssets in ThisBuild := {
  val npmDirectory = (npmUpdate in Compile).value
  IO.copyDirectory(baseDirectory.value / "src" / "main" / "static", npmDirectory, overwrite = true)
  streams.value.log.info("Copy assets to working directory")
}


fastOptJS in Compile := {
  prepareAssets.value
  (fastOptJS in Compile).value
}

fullOptJS in Compile := {
  prepareAssets.value
  (fullOptJS in Compile).value
}

Test / testOptions += Tests.Argument("-oF")