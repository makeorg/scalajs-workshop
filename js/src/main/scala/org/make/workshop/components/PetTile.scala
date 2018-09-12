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
import org.make.workshop.facades.Translate
import org.make.workshop.routes.Router.{PetDetailsPage, PetPages}
import org.make.workshop.shared.Pet
import org.make.workshop.styles.PetTileStyles
import scalacss.internal.mutable.GlobalRegistry

object PetTile {
  case class PetTileProps(pet: Pet, router: RouterCtl[PetPages])

  val tileStyles: PetTileStyles = GlobalRegistry[PetTileStyles].get

  class Backend($ : BackendScope[PetTileProps, Unit]) {

    def goToPetDetails(event: ReactEvent): Callback = {
      event.preventDefault
      for {
        props <- $.props
        _ <- props.router.set(PetDetailsPage(props.pet.id))
      } yield {}
    }

    def render(props: PetTileProps): VdomElement =
      <.article(
        ^.className := tileStyles.petItem.htmlClass,
        ^.key := props.pet.id,
        <.a(
          ^.href := "#",
          ^.onClick ==> goToPetDetails,
          <.img(^.className := tileStyles.petImage.htmlClass,
                ^.src := props.pet.picture,
                ^.alt := props.pet.name)
        ),
        <.section(
          ^.className := tileStyles.petInfos.htmlClass,
          <.h3(
            ^.className := tileStyles.petName.htmlClass,
            <.a(^.href := "#", ^.onClick ==> goToPetDetails, props.pet.name)),
          Translate(className = Some(tileStyles.petAge.htmlClass),
                    value = "tile.age",
                    replacements = Map("age" -> props.pet.age),
                    tag = Some("p")),
          <.p(^.className := tileStyles.petDescription.htmlClass,
              props.pet.description)
        )
      )
  }

  private val component = ScalaComponent
    .builder[PetTileProps]("PetTile")
    .renderBackend[Backend]
    .build

  def apply(
      pet: Pet,
      router: RouterCtl[PetPages]): Unmounted[PetTileProps, Unit, Backend] =
    component(PetTileProps(pet, router))

}
