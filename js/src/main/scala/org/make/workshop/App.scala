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

package org.make.workshop

import org.make.workshop.facades.{I18n, translationsEnGB, translationsFrFR}
import org.make.workshop.routes.Router
import org.make.workshop.styles._
import org.scalajs.dom.document
import scalacss.DevDefaults._
import scalacss.ScalaCssReact._
import scalacss.internal.mutable.GlobalRegistry

import scala.scalajs.js

object App {

  I18n.setTranslations(
    js.Dictionary[js.Object]("en" -> translationsEnGB, "fr" -> translationsFrFR)
  )

  I18n.setLocale("en")

  def main(args: Array[String]): Unit = {
    GlobalRegistry.register(new BaseStyles,
                            new LayoutStyles,
                            new HeaderStyles,
                            new FooterStyles,
                            new PetListStyles,
                            new PetTileStyles,
                            new PetDetailsStyles,
                            new FormStyles)
    Router().renderIntoDOM(
      document.getElementById("app")
    )
    GlobalRegistry.addToDocumentOnRegistration()
  }

}
