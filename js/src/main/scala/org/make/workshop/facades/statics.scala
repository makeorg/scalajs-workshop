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

package org.make.workshop.facades

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/* TRANSLATIONS */

@js.native
@JSImport("./i18n/en_GB.json", "default")
object translationsEnGB extends js.Object

@js.native
@JSImport("./i18n/fr_FR.json", "default")
object translationsFrFR extends js.Object

/* IMAGES */
@js.native
@JSImport("./images/dog.png", "default")
object dog extends js.Object
