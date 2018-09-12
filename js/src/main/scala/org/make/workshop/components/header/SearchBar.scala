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

package org.make.workshop.components.header

import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, ScalaComponent, _}
import org.make.workshop.routes.Router.{PetPages, SearchPage}
import org.make.workshop.styles.HeaderStyles
import scalacss.ScalaCssReact._
import scalacss.internal.mutable.GlobalRegistry

object SearchBar {

  case class SearchBarProps(router: RouterCtl[PetPages])
  case class SearchBarState(text: String)

  class Backend(bs: BackendScope[SearchBarProps, SearchBarState]) {

    def onTextChange(event: ReactEventFromInput): Callback = {
      val newValue = event.target.value
      bs.modState(_.copy(text = newValue))
    }

    def onSubmit(event: ReactEventFromHtml): Callback = {
      event.preventDefault()
      for {
        props <- bs.props
        state <- bs.state
        action <- props.router.set(SearchPage(state.text))
      } yield action
    }

    val headerStyles = GlobalRegistry[HeaderStyles].get

    def render(s: SearchBarState): VdomElement = {
      <.form(
        headerStyles.headerForm,
        ^.onSubmit ==> onSubmit,
        <.input(headerStyles.searchInput,
                ^.`type` := "text",
                ^.placeholder := "Rechercher",
                ^.onChange ==> onTextChange)
      )
    }
  }

  private val component = ScalaComponent
    .builder[SearchBarProps]("SearchBar")
    .initialState(SearchBarState(""))
    .renderBackend[Backend]
    .build

  def apply(props: SearchBarProps)
    : Unmounted[SearchBarProps, SearchBarState, Backend] = component(props)
}
