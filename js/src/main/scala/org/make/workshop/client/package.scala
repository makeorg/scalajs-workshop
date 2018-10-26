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

package org.make.workshop

import io.circe.Decoder
import io.circe.parser.parse
import org.scalajs.dom.experimental.Response

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js.Promise

package object client {

  implicit class RichResponse(val self: Response) extends AnyVal {
    def as[T](implicit decoder: Decoder[T]): Future[T] = {
      self
        .text()
        .toFuture
        .map { txt =>
          parse(txt).flatMap(_.as[T])
        }
        .flatMap {
          case Left(value)  => Future.failed(value)
          case Right(value) => Future.successful(value)
        }
    }
  }

  implicit class RichPromiseResponse(val self: Promise[Response])
      extends AnyVal {
    def decodeAs[T](implicit decoder: Decoder[T]): Future[T] = {
      self.toFuture.flatMap(_.as[T])
    }
  }

}
