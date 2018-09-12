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
import japgolly.scalajs.react.vdom.html_<^._
import org.make.workshop.facades.{I18n, Translate}
import org.make.workshop.styles.FooterStyles
import scalacss.ScalaCssReact._
import scalacss.internal.mutable.GlobalRegistry

object ChangeLanguage {

  case class ChangeLanguageState(language: String)

  class ChangeLanguageBackend($ : BackendScope[_, ChangeLanguageState]) {

    def changeLanguage(currentLanguage: String): Callback = {
      val locale = if (currentLanguage == "en") "fr" else "en"
      Callback(I18n.setLocale(locale)) >> $.setState(
        ChangeLanguageState(locale))
    }

    val footerStyles = GlobalRegistry[FooterStyles].get

    def render(state: ChangeLanguageState): VdomElement = {
      <.button(footerStyles.itemButton,
               ^.onClick --> changeLanguage(state.language),
               Translate("changeLanguage"))
    }
  }

  private val component = ScalaComponent
    .builder[Unit]("ChangeLanguage")
    .initialState[ChangeLanguageState](ChangeLanguageState("en"))
    .renderBackend[ChangeLanguageBackend]
    .build

  def apply(): Unmounted[Unit, ChangeLanguageState, ChangeLanguageBackend] =
    component()
}
