import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

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


enablePlugins(DockerPlugin)

packageName in Docker := "scalajs-workshop-frontend"

val nginxContentDirectory = "/usr/share/nginx/html/scalaio"
val nginxEnvParams = "/etc/nginx/env_params"
val nginxPerlModule = "/etc/nginx/modules/ngx_http_perl_module.so"

dockerCommands := Seq(
  Cmd("FROM", "nginx:1.13.3-perl"),
  Cmd("ENV", "API_URL", "http://localhost:9000"),
  ExecCmd("RUN", "rm", "/etc/nginx/conf.d/default.conf"),
  Cmd("COPY", "dist", nginxContentDirectory),
  Cmd("COPY", "conf/nginx.conf", "/etc/nginx/conf.d/scalaio.conf"),
  Cmd("COPY", "conf/env_params", nginxEnvParams),
  ExecCmd("RUN", "chmod", "-R", "+rw", nginxContentDirectory),
  ExecCmd("CMD", "nginx", "-g", s"daemon off; load_module $nginxPerlModule; include $nginxEnvParams;")
)

val copyDockerResources: TaskKey[String] = taskKey[String]("copy directories")

copyDockerResources := {
  val files = (webpack in (Compile, fullOptJS)).value
  streams.value.log.info("Copying resources to the docker directory")
  val base = baseDirectory.value
  val dockerDirectory = base / "target" / "docker" / "stage"
  dockerDirectory.mkdirs()
  IO.copyDirectory(base / "src" / "main" / "universal", dockerDirectory, overwrite = true)
  IO.copyDirectory(
    base / "target" / s"scala-${scalaBinaryVersion.value}" / "scalajs-bundler" / "main" / "dist",
    dockerDirectory / "dist",
    overwrite = true
  )
  "Done"
}

publishLocal := {
  Def.sequential(copyDockerResources, publishLocal in Docker).value
}

publish := {
  Def.sequential(copyDockerResources, publish in Docker).value
}
