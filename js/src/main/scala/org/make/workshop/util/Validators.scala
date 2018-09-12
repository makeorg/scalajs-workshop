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

package org.make.workshop.util

import org.make.workshop.facades.I18n

object Validators {

  case class Error(`type`: String, message: String)

  private def errorMessageFromPredicate(predicate: Boolean,
                                        errorName: String): Option[String] =
    if (predicate) {
      Some(I18n.t(s"errors.$errorName"))
    } else {
      None
    }

  def required: String => Option[Error] = value => {
    errorMessageFromPredicate(value.isEmpty, "required").map { errorMessage =>
      Error("required", errorMessage)
    }
  }

  def isEmail: String => Option[Error] = value => {
    val predicate =
      !value.contains("@") || value.lastIndexOf(".") == value.length - 1
    errorMessageFromPredicate(predicate, "email").map { errorMessage =>
      Error("isEmail", errorMessage)
    }
  }

  def isNumericGte(treshold: Int): String => Option[Error] = value => {
    val predicate = value.isEmpty || value.toInt < treshold
    errorMessageFromPredicate(predicate, "legalAgeLimit").map { errorMessage =>
      Error("isNumericGte", errorMessage)
    }
  }
}
