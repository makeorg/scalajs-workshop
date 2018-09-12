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

package org.make.workshop.shared

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder, Json}

case class Pet(id: String,
               name: String,
               race: Race,
               age: Int,
               picture: String,
               description: String)

object Pet {
  implicit val encoder: Encoder[Pet] = deriveEncoder[Pet]
  implicit val decoder: Decoder[Pet] = deriveDecoder[Pet]
}

sealed trait Race {
  def name: String
}

object Race {

  case object Cat extends Race { override val name: String = "cat" }
  case object Dog extends Race { override val name: String = "dog" }
  case object Parrot extends Race { override val name: String = "parrot" }
  case object Lizard extends Race { override val name: String = "lizard" }
  case object Unicorn extends Race { override val name: String = "Unicorn" }

  val races = Map(
    Cat.name -> Cat,
    Dog.name -> Dog,
    Parrot.name -> Parrot,
    Lizard.name -> Lizard,
    Unicorn.name -> Unicorn,
  )

  implicit val encoder: Encoder[Race] = (race: Race) =>
    Json.fromString(race.name)
  implicit val decoder: Decoder[Race] =
    Decoder.decodeString.emap(
      raceName =>
        races
          .get(raceName)
          .map(Right.apply)
          .getOrElse(Left(s"$raceName is not a race")))
}
