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

class FooterStyles extends StyleSheet.Inline {
  import dsl._

  val footerInner = style(
    display.flex,
    width(100.%%),
    margin(0.px),
    padding(0.px),
    justifyContent.spaceAround,
    alignItems.center,
    listStyle := "none"
  )

  val itemLink = style(
    fontWeight.bold,
    textTransform.uppercase
  )

  val itemButton = style(
    fontWeight.bold,
    textTransform.uppercase,
    padding(0.px),
    backgroundColor.transparent,
    border.none,
    &.hover(textDecoration := "underline")
  )
}
