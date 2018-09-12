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

import japgolly.scalajs.react.extra.router.{BaseUrl, Path}
import japgolly.scalajs.react.test.MockRouterCtl.SetUrlToPage
import japgolly.scalajs.react.test._
import org.make.workshop.routes.Router.{PetDetailsPage, PetPages}
import org.make.workshop.shared.{Pet, Race}
import org.make.workshop.styles.PetTileStyles
import scalacss.internal.mutable.GlobalRegistry

class PetTileTest extends UnitTest {

  feature("Pet tile") {
    scenario("display tile") {

      GlobalRegistry.register(new PetTileStyles)
      val pet =
        Pet("pet-id", "pet name", Race.Unicorn, 99, "", "some description")

      val router: MockRouterCtl[PetPages] =
        MockRouterCtl(BaseUrl("http://localhost:12345"), {
          case PetDetailsPage(id) => Path(s"/pets/$id")
          case _                  => Path("/404")
        })

      ReactTestUtils
        .withRenderedIntoDocument(PetTile(pet, router)) { component =>
          val link =
            component.getDOMNode.toElement.get.getElementsByTagName("a").item(0)

          Simulate.click(link)

          router.events() should contain(
            SetUrlToPage(PetDetailsPage("pet-id"), Path("/pets/pet-id")))
        }

    }
  }

}
