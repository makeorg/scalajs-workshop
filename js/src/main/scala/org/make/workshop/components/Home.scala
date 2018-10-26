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
import org.make.workshop.styles.PetListStyles
import scalacss.internal.mutable.GlobalRegistry

import scala.concurrent.ExecutionContext.Implicits.global

object Home {

  case class HomeProps(router: RouterCtl[PetPages])
  case class HomeState(pets: Seq[Pet])

  val homeStyles: PetListStyles = GlobalRegistry[PetListStyles].get

  class Backend($ : BackendScope[HomeProps, HomeState]) {
    def render(state: HomeState, props: HomeProps): VdomElement =
      <.div(
        Translate("home.message",
                  replacements = Map("name" -> "you"),
                  tag = Some("h2")),
        Translate("home.description", tag = Some("p")),
//        <.ul(
//          ^.className := homeStyles.petList.htmlClass,
//          state.pets.toVdomArray(
//            pet =>
//              <.li(^.className := homeStyles.petItem.htmlClass,
//                   ^.key := pet.id,
//                   React.Fragment(PetTile(pet, props.router))))
//        )
      )
  }

  val component = ScalaComponent
    .builder[HomeProps]("Home")
    .initialState(HomeState(Seq.empty))
    .renderBackend[Backend]
    .componentDidMount { component =>
      PetService
        .searchPets()
        .map(pets => component.modState(_.copy(pets = pets)))
        .toCallback
    }
    .build

  def apply(
      router: RouterCtl[PetPages]): Unmounted[HomeProps, HomeState, Backend] =
    component(HomeProps(router))
}
