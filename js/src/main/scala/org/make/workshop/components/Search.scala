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

import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.all.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, React, ScalaComponent}
import org.make.workshop.client.PetService
import org.make.workshop.routes.Router.PetPages
import org.make.workshop.shared.Pet
import org.make.workshop.styles.PetListStyles
import scalacss.internal.mutable.GlobalRegistry

import scala.concurrent.ExecutionContext.Implicits.global

object Search {

  case class SearchProps(text: String, router: RouterCtl[PetPages])
  case class SearchState(pets: Seq[Pet])

  private val searchStyles = GlobalRegistry[PetListStyles].get

  class Backend(bs: BackendScope[SearchProps, SearchState]) {
    def render(state: SearchState, props: SearchProps): VdomElement = {
      <.section(
        SearchTitle(props.text),
        <.ul(
          ^.className := searchStyles.petList.htmlClass,
          state.pets.toVdomArray(
            _ =>
              React.Fragment(
                <.li(^.className := searchStyles.petItem.htmlClass, ???)))
        )
      )
    }
  }

  private val component = ScalaComponent
    .builder[SearchProps]("SearchPage")
    .initialState(SearchState(Seq.empty))
    .renderBackend[Backend]
    .componentWillMount { $ =>
      PetService
        .searchPets(Some($.props.text))
        .map(pets => $.modState(_.copy(pets = pets)))
        .toCallback
    }
    .componentWillReceiveProps { $ =>
      PetService
        .searchPets(Some($.nextProps.text))
        .map(pets => $.modState(_.copy(pets = pets)))
        .toCallback
    }
    .build

  def apply(props: SearchProps): Unmounted[SearchProps, SearchState, Backend] =
    component(props)

}
