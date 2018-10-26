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

class HeaderStyles extends StyleSheet.Inline {
  import dsl._

  val headerInner = style(
    display.flex,
    width(100.%%),
    justifyContent.spaceBetween,
    alignItems.center
  )

  val mainTitle = style(
    minWidth(235.px)
  )

  val titleImage = style(
    margin(0.px, 5.px)
  )

  val headerForm = style(
    width(100.%%)
  )

  val searchInput = style(
    fontSize(30.px),
    width(100.%%),
    padding(0.px, 10.px),
    backgroundColor(transparent),
    borderTop.none,
    borderLeft.none,
    borderRight.none,
    borderBottom(2.px, solid, rgb(85, 85, 85)),
    &.focus(
      outline.none,
      borderBottom(4.px, solid, rgb(85, 85, 85))
    )
  )
}
