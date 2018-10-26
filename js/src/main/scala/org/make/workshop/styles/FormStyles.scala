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

package org.make.workshop.styles

import scalacss.DevDefaults._

class FormStyles extends StyleSheet.Inline {
  import dsl._

  val halfContent = style(
    display.flex,
    flexFlow := "column",
    width(50.%%),
    height(100.%%),
    padding(0.px, 10.px),
    justifyContent.center,
    alignItems.center
  )

  val formWrapper = style(
    display.flex,
    width(100.%%),
    flexFlow := "column",
    justifyContent.center,
    alignItems.center
  )

  val formTitle = style(
    margin(15.px, 0.px)
  )

  val input = style(
    fontSize(16.px),
    width(100.%%),
    padding(10.px),
    backgroundColor(transparent),
    borderTop.none,
    borderLeft.none,
    borderRight.none,
    borderBottom(1.5.px, solid, rgb(85, 85, 85)),
    &.focus(
      outline.none,
      borderBottom(3.px, solid, rgb(85, 85, 85))
    )
  )

  val checkboxWrapper = style(
    display.flex,
    justifyContent.flexStart,
    alignItems.center,
    width(100.%%),
    margin(15.px, 0.px, 0.px)
  )

  val checkboxItem = style(
    marginRight(5.px)
  )

  val checkboxLabel = style(
    fontSize(18.px),
    margin(0.px)
  )

  val error = style(margin(10.px, 0.px, 0.px), color(rgb(186, 52, 52)))

  val submitWrapper = style(
    display.flex,
    justifyContent.flexEnd,
    width(100.%%),
    marginTop(15.px)
  )

  val submitButton = style(
    color(rgb(252, 252, 252)),
    fontSize(18.px),
    fontWeight.bold,
    textTransform.uppercase,
    backgroundColor(rgb(85, 85, 85)),
    border(1.px, solid, rgb(85, 85, 85)),
    borderRadius(25.px),
    padding(10.px, 20.px, 5.px)
  )
}
