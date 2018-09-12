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

class PetListStyles extends StyleSheet.Inline {
  import dsl._

  val petList = style(
    display.grid,
    width(100.%%),
    padding(0.px),
    gridTemplateColumns := "repeat(3, 1fr)",
    listStyle := "none"
  )

  val petItem = style(
    width(100.%%),
    height(100.%%),
    padding(10.px)
  )
}
