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

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import de.knutwalker.akka.http.support.CirceHttpSupport
import org.make.workshop.data.Pets

object PetApi extends Directives with CirceHttpSupport {

  def pets: Route = get {
    path("pets") {
      parameters('filter.as[String].?) { maybeFilter =>
        val results = Pets.filteredPets(maybeFilter)
        complete(results)
      }
    }
  }

  def petDetails: Route = get {
    path("pet" / Segment) { petId =>
      Pets.all.find(_.id == petId) match {
        case Some(pet) => complete(pet)
        case None      => complete(StatusCodes.NotFound)
      }
    }
  }

  def adopt: Route = post {
    path("pets" / "adopt" / Segment) { petId =>
      Pets.adopt(petId)
      complete(StatusCodes.OK)
    }
  }

  val routes: Route = pets ~ petDetails ~ adopt
}
