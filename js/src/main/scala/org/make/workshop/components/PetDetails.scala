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

package org.make.workshop.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._
import org.make.workshop.client.PetService
import org.make.workshop.facades.Translate
import org.make.workshop.routes.Router.PetPages
import org.make.workshop.shared.Pet
import org.make.workshop.styles.PetDetailsStyles
import scalacss.internal.mutable.GlobalRegistry

import scala.concurrent.ExecutionContext.Implicits.global

object PetDetails {
  case class PetDetailsProps(petId: String, router: RouterCtl[PetPages])
  case class PetDetailsState(pet: Option[Pet])
  val detailsStyles: PetDetailsStyles = GlobalRegistry[PetDetailsStyles].get

  class Backend($ : BackendScope[PetDetailsProps, PetDetailsState]) {
    def render(state: PetDetailsState, props: PetDetailsProps): VdomElement = {
      React.Fragment(
        state.pet.map { pet =>
          <.div(
            ^.className := detailsStyles.detailswrapper.htmlClass,
            <.div(
              ^.className := detailsStyles.halfContent.htmlClass,
              <.h2(^.className := detailsStyles.petName.htmlClass, pet.name),
              <.img(^.className := detailsStyles.petImage.htmlClass,
                    ^.src := pet.picture,
                    ^.alt := pet.name),
              Translate(value = "details.age",
                        replacements = Map("age" -> pet.age)),
              <.p(^.className := detailsStyles.petDescription.htmlClass,
                  pet.description)
            ),
            AdoptPet(pet, props.router)
          )
        }
      )
    }
  }

  private val component = ScalaComponent
    .builder[PetDetailsProps]("PetDetails")
    .initialState[PetDetailsState](PetDetailsState(None))
    .renderBackend[Backend]
    .componentDidMount { $ =>
      PetService
        .getPet($.props.petId)
        .map(pet => $.modState(_.copy(pet = pet)))
        .toCallback
    }
    .build

  def apply(petId: String, router: RouterCtl[PetPages])
    : Unmounted[PetDetailsProps, PetDetailsState, Backend] =
    component(PetDetailsProps(petId, router))
}
