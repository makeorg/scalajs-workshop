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

package org.make.workshop.routes

import japgolly.scalajs.react.React
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.extra.OnUnmount
import japgolly.scalajs.react.extra.router.{
  BaseUrl,
  Resolution,
  RouterConfigDsl,
  RouterCtl
}
import japgolly.scalajs.react.vdom.html_<^._
import org.make.workshop.components.Search.SearchProps
import org.make.workshop.components.header.Header
import org.make.workshop.components.header.Header.HeaderProps
import org.make.workshop.components.{Footer, Home, PetDetails, Search}
import org.make.workshop.styles.LayoutStyles
import scalacss.ScalaCssReact._
import scalacss.internal.mutable.GlobalRegistry

import scala.util.matching.Regex

object Router {

  sealed trait PetPages
  case object HomePage extends PetPages
  case class PetDetailsPage(id: String) extends PetPages
  case class SearchPage(text: String) extends PetPages

  private def layout(c: RouterCtl[PetPages],
                     r: Resolution[PetPages]): VdomElement = {
    val layoutStyles = GlobalRegistry[LayoutStyles].get

    React.Fragment(
      <.div(
        layoutStyles.appWrapper,
        <.header(layoutStyles.headerWrapper, Header(HeaderProps(c))),
        <.main(layoutStyles.mainWrapper, r.render()),
        <.footer(layoutStyles.footerWrapper, Footer())
      )
    )
  }

  private val baseUrl = BaseUrl.fromWindowOrigin
  private val routerConfig = RouterConfigDsl[PetPages].buildConfig { dsl =>
    import dsl._

    val petDetails = {
      dynamicRouteCT("#" / "pet" / string(".*").caseClass[PetDetailsPage]) ~> dynRenderR {
        (page: PetDetailsPage, routerCtl: RouterCtl[PetPages]) =>
          PetDetails(page.id, routerCtl)
      }
    }

    val searchPage = {
      val SearchRegex: Regex = "\\?q=(.*)".r

      val parse: PartialFunction[String, Option[SearchPage]] = {
        case SearchRegex(q) => Some(SearchPage(q))
        case _              => None
      }

      dynamicRouteCT[SearchPage](
        "#" / "search" / remainingPath
          .pmap(parse)(page => s"?q=${page.text}")) ~> dynRenderR(
        (page, router) => Search(SearchProps(page.text, router)))
    }

    val home = staticRoute(root, HomePage) ~> renderR(Home(_))

    (
      trimSlashes
        | home
        | petDetails
        | searchPage
    ).notFound(HomePage)
      .renderWith(layout)

  }

  private val router =
    japgolly.scalajs.react.extra.router.Router(baseUrl, routerConfig)

  def apply(): Unmounted[Unit, Resolution[PetPages], OnUnmount.Backend] =
    router()

}
