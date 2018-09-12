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

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Js.{RawMounted, UnmountedWithRawType}

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-i18nify", "I18n")
object I18n extends js.Object {
  def setTranslations(translations: js.Any,
                      rerenderComponents: Boolean = true): Unit = js.native

  def setLocale(locale: String): js.Any = js.native
  def t(text: String, params: js.UndefOr[Any] = js.Any): String = js.native
  def l(date: js.Any, params: js.UndefOr[Any] = js.Any): String = js.native
}

@js.native
@JSImport("react-i18nify", "Translate")
object NativeTranslate extends js.Object

object Translate {
  @js.native
  trait TranslateProps extends js.Object {
    val value: String
    val count: js.UndefOr[Int]
    val dangerousHTML: js.UndefOr[Boolean]
    val tag: js.UndefOr[String]
    val className: js.UndefOr[String]
  }

  def props(value: String,
            count: Option[Int] = None,
            dangerousHTML: Option[Boolean] = None,
            tag: Option[String] = None,
            className: Option[String] = None,
            replacements: Map[String, Any] = Map.empty): TranslateProps = {
    (replacements ++ Map(
      "value" -> value,
      "count" -> count.orUndefined,
      "tag" -> tag.orUndefined,
      "className" -> className.orUndefined,
      "dangerousHTML" -> dangerousHTML.orUndefined)).toJSDictionary
      .asInstanceOf[TranslateProps]
  }

  private val component =
    JsComponent[TranslateProps, Children.Varargs, Null](NativeTranslate)

  def apply(value: String,
            count: Option[Int] = None,
            dangerousHTML: Option[Boolean] = None,
            replacements: Map[String, Any] = Map.empty,
            tag: Option[String] = None,
            className: Option[String] = None)
    : UnmountedWithRawType[TranslateProps,
                           Null,
                           RawMounted[TranslateProps, Null]] =
    component(
      props(value, count, dangerousHTML, tag, className, replacements))()
}
