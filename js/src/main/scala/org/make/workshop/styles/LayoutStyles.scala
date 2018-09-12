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

class LayoutStyles extends StyleSheet.Inline {
  import dsl._

  val appWrapper = style(
    display.flex,
    flexFlow := "column",
    height(100.vh),
    justifyContent.spaceBetween,
    alignItems.center,
    backgroundColor(rgb(248, 248, 248))
  )

  val headerWrapper = style(
    position.relative,
    zIndex(1),
    display.flex,
    width(100.vw),
    height(80.px),
    padding(20.px),
    alignItems.center,
    backgroundColor(rgb(238, 238, 238)),
    boxShadow := s"0 4px 8px 0 rgba(0, 0, 0, 0.3)"
  )

  val footerWrapper = style(
    position.relative,
    zIndex(1),
    display.flex,
    width(100.vw),
    height(64.px),
    padding(20.px),
    alignItems.center,
    backgroundColor(rgb(238, 238, 238)),
    boxShadow := s"0 -4px 8px 0 rgba(0, 0, 0, 0.3)"
  )

  val mainWrapper = style(
    display.flex,
    flexFlow := "column",
    width(100.vw),
    height :=! s"calc(100vh - 144px)",
    padding(25.px),
    overflowY.auto
  )
}
