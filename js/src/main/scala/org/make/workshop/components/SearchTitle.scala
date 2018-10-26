package org.make.workshop.components

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.vdom.html_<^._

object SearchTitle {

  case class SearchTitleProps(text: String)

  private val component = ScalaComponent
    .builder[SearchTitleProps]("SearchTitle")
    .render_P { props =>
      <.p(s"results for '${props.text}'")
    }
    .build

  def apply(text: String): Unmounted[SearchTitleProps, Unit, Unit] =
    component(SearchTitleProps(text))
}
