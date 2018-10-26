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

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._
import org.make.workshop.components.header.SearchBar.SearchBarProps
import org.make.workshop.facades.dog
import org.make.workshop.routes.Router.{HomePage, PetPages}
import org.make.workshop.styles.HeaderStyles
import scalacss.ScalaCssReact._
import scalacss.internal.mutable.GlobalRegistry

object Header {

  case class HeaderProps(router: RouterCtl[PetPages])
  val headerStyles = GlobalRegistry[HeaderStyles].get

  private val component = ScalaComponent
    .builder[HeaderProps]("Header")
    .renderP((_, props) =>
      <.div(
        headerStyles.headerInner,
        <.h1()(
          headerStyles.mainTitle,
          <.a(^.href := "#",
              props.router.setOnClick(HomePage),
              "Adopt",
              <.img(^.className := headerStyles.titleImage.htmlClass,
                    ^.src := dog.toString,
                    ^.alt := "",
              )(),
              "pet")
        ),
        SearchBar(SearchBarProps(props.router))
    ))
    .build

  def apply(props: HeaderProps): Unmounted[HeaderProps, Unit, Unit] =
    component(props)
}
