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

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.vdom.html_<^._
import org.make.workshop.styles.FooterStyles
import scalacss.ScalaCssReact._
import scalacss.internal.mutable.GlobalRegistry

object Footer {
  val footerStyles = GlobalRegistry[FooterStyles].get
  private val component = ScalaComponent
    .builder[Unit]("Header")
    .renderStatic(
      <.ul(
        footerStyles.footerInner,
        <.li(
          <.a(footerStyles.itemLink, ^.href := "https://make.org", "Make.org")
        ),
        <.li(
          <.a(footerStyles.itemLink,
              ^.href := "https://about.make.org",
              "About us")
        ),
        <.li(
          <.a(footerStyles.itemLink,
              ^.href := "https://about.make.org/jobs",
              "Jobs")
        ),
        <.li(
          <.a(footerStyles.itemLink,
              ^.href := "https://about.make.org/contact",
              "Contact")
        ),
        <.li(ChangeLanguage())
      )
    )
    .build

  def apply(): Unmounted[Unit, Unit, Unit] = component()
}
