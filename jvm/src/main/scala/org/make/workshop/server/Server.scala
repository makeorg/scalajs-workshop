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

package org.make.workshop.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.model.headers.{
  `Access-Control-Allow-Credentials`,
  `Access-Control-Allow-Methods`,
  `Access-Control-Allow-Origin`
}
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.ActorMaterializer
import de.knutwalker.akka.http.support.CirceHttpSupport

import scala.collection.immutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Server extends App with Directives with CirceHttpSupport {

  private implicit val system: ActorSystem = ActorSystem()
  private implicit val materializer: ActorMaterializer = ActorMaterializer()

  private val routes: Route =
    respondWithDefaultHeaders {
      immutable.Seq(
        `Access-Control-Allow-Methods`(
          HttpMethods.POST,
          HttpMethods.GET,
          HttpMethods.PUT,
          HttpMethods.PATCH,
          HttpMethods.DELETE
        ),
        `Access-Control-Allow-Credentials`(true),
        `Access-Control-Allow-Origin`(origin = "http://localhost:9009")
      )
    }(PetApi.routes)

  private val bindingFuture: Future[ServerBinding] =
    Http().bindAndHandle(routes, "0.0.0.0", 9000)

  bindingFuture
    .map { serverBinding =>
      println(s"Workshop Backend bound to ${serverBinding.localAddress} ")
    }
    .onComplete {
      case util.Failure(ex) =>
        ex.printStackTrace()
        system.terminate()
      case _ =>
    }
}
