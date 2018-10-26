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

class PetTileStyles extends StyleSheet.Inline {
  import dsl._

  val petItem = style(
    display.flex,
    height(100.%%),
    flexFlow := "column",
    alignItems.center,
    justifyContent.spaceBetween,
    padding(20.px),
    backgroundColor(rgb(232, 232, 232)),
    gridColumn := "1/1",
    gridRow := "1",
    boxShadow := s"0 4px 8px 0 rgba(0, 0, 0, 0.3)"
  )

  val petImage = style(
    maxWidth(100.%%),
    height.auto
  )

  val petInfos = style(
    width(100.%%),
    borderTop(1.px, solid, rgb(85, 85, 85)),
    marginTop(20.px),
    paddingTop(20.px)
  )

  val petName = style(
    fontWeight.bold,
    margin(0.px)
  )

  val petAge = style(
    margin(0.px)
  )

  val petDescription = style(
    margin(0.px),
    fontStyle.italic
  )
}
