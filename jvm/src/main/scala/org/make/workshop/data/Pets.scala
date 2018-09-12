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

package org.make.workshop.data

import org.make.workshop.shared.Pet

object Pets {
  var all = Seq(Cats.felix,
                Cats.minouchette,
                Cats.norminet,
                Dogs.medor,
                Dogs.annivia,
                Dogs.puppy,
                Parrots.coco,
                Parrots.jack,
                Lizards.balthazar,
                Unicorns.charlie)

  def filteredPets(maybeFilter: Option[String]): Seq[Pet] = {
    all.filter { pet =>
      maybeFilter match {
        case None => true
        case Some(filter)
            if pet.name.toLowerCase().contains(filter.toLowerCase()) =>
          true
        case Some(filter)
            if pet.race.name.toLowerCase().contains(filter.toLowerCase()) =>
          true
        case Some(filter)
            if pet.description
              .toLowerCase()
              .contains(filter.toLowerCase()) =>
          true
        case _ => false
      }
    }
  }

  def adopt(petId: String): Unit = {
    all = all.filter(_.id != petId)
  }

}
