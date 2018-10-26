package org.make.workshop

import japgolly.scalajs.react.extra.router.{BaseUrl, Path}
import japgolly.scalajs.react.test.MockRouterCtl

object TestUtils {

  def createTestRouter[T](routing: T => Path): MockRouterCtl[T] = {
    MockRouterCtl(BaseUrl("http://localhost:12345"), routing)
  }

}
