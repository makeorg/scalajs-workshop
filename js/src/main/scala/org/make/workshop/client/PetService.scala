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

package org.make.workshop.client

import io.circe.DecodingFailure
import org.make.workshop.shared.Pet
import org.scalajs.dom.experimental.{Fetch, HttpMethod, RequestInit}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js

object PetService {

  private val backendBaseUrl = "http://localhost:9000"

  def searchPets(maybeFilter: Option[String] = None): Future[Seq[Pet]] = {
    val parameter = maybeFilter.map(filter => s"?filter=$filter").getOrElse("")

    Fetch
      .fetch(
        info = s"$backendBaseUrl/pets$parameter",
        init = RequestInit(method = HttpMethod.GET)
      )
      .decodeAs[Seq[Pet]]
  }

  def getPet(id: String): Future[Option[Pet]] =
    Fetch
      .fetch(info = s"$backendBaseUrl/pet/$id",
             init = RequestInit(method = HttpMethod.GET))
      .decodeAs[Option[Pet]]
      .recover {
        case _: DecodingFailure =>
          js.Dynamic.global.console.log("decode failed")
          None
        case error =>
          js.Dynamic.global.console.log(error.toString)
          None
      }

  def adopt(id: String): Future[Unit] =
    Fetch
      .fetch(
        info = s"$backendBaseUrl/pets/adopt/$id",
        init = RequestInit(method = HttpMethod.POST)
      )
      .toFuture
      .map(_ => {})

}
