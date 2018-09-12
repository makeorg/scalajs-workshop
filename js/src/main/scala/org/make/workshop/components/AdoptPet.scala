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

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._
import org.make.workshop.client.PetService
import org.make.workshop.facades.I18n
import org.make.workshop.routes.Router.{HomePage, PetPages}
import org.make.workshop.shared.Pet
import org.make.workshop.styles.FormStyles
import org.make.workshop.util.Validators
import scalacss.ScalaCssReact._
import scalacss.internal.mutable.GlobalRegistry

import scala.concurrent.ExecutionContext.Implicits.global

object AdoptPet {
  case class AdoptPetProps(pet: Pet, router: RouterCtl[PetPages])
  case class AdoptPetState(errors: Map[String, Seq[Validators.Error]])

  class Backend($ : BackendScope[AdoptPetProps, AdoptPetState]) {

    def validate: CallbackTo[Boolean] = {
      $.state.map { state =>
        state.errors.isEmpty
      }
    }

    def adoptPet(event: ReactEventFromHtml): Callback = {
      event.preventDefault()

      for {
        props <- $.props
        valid <- validate
        _ <- Callback.when(valid) {
          PetService
            .adopt(props.pet.id)
            .map(_ => Callback {})
            .toCallback
            .flatMap(_ => props.router.set(HomePage))
        }
      } yield {}
    }

    val fieldsValidations
      : Map[String, Seq[String => Option[Validators.Error]]] =
      Map[String, Seq[String => Option[Validators.Error]]](
        "age" -> Seq(Validators.isNumericGte(18)),
        "name" -> Seq(Validators.required),
        "email" -> Seq(Validators.required, Validators.isEmail)
      )

    def validateField(fieldName: String)(
        event: ReactEventFromInput): Callback = {
      val fieldValue = event.target.value
      $.state.flatMap { state =>
        val fieldErrors: Seq[Validators.Error] = fieldsValidations
          .get(fieldName)
          .map { validations =>
            validations.flatMap(_(fieldValue))
          }
          .getOrElse(Seq.empty)
        if (fieldErrors.nonEmpty) {
          $.modState(_ =>
            state.copy(errors = state.errors ++ Map(fieldName -> fieldErrors)))
        } else {
          $.modState(_ =>
            state.copy(errors = state.errors.filterKeys(_ != fieldName)))
        }
      }
    }

    def renderErrors(field: String,
                     stateErrors: Map[String, Seq[Validators.Error]])
      : Option[VdomElement] = {
      stateErrors.get(field).map { errors =>
        React.Fragment(
          errors.toVdomArray(
            error =>
              <.p(^.key := s"$field-${error.`type`}",
                  formStyles.error,
                  error.message)))
      }
    }

    val formStyles: FormStyles = GlobalRegistry[FormStyles].get

    def render(state: AdoptPetState): VdomElement = {
      <.div(
        ^.className := formStyles.halfContent.htmlClass,
        <.h3("Adopt me!"),
        <.form(
          ^.className := formStyles.formWrapper.htmlClass,
          <.input(
            ^.className := formStyles.input.htmlClass,
            ^.`type` := "text",
            ^.name := "name",
            ^.placeholder := I18n.t("details.adoptionForm.name"),
            ^.onBlur ==> validateField("name")
          ),
          renderErrors("name", state.errors),
          <.input(
            ^.className := formStyles.input.htmlClass,
            ^.`type` := "number",
            ^.name := "age",
            ^.placeholder := I18n.t("details.adoptionForm.age"),
            ^.onBlur ==> validateField("age")
          ),
          renderErrors("age", state.errors),
          <.input(
            ^.className := formStyles.input.htmlClass,
            ^.`type` := "mail",
            ^.name := "email",
            ^.placeholder := I18n.t("details.adoptionForm.email"),
            ^.onBlur ==> validateField("email")
          ),
          renderErrors("email", state.errors),
          <.input(
            ^.className := formStyles.input.htmlClass,
            ^.`type` := "text",
            ^.name := "phone",
            ^.placeholder := I18n.t("details.adoptionForm.phone"),
            ^.onBlur ==> validateField("phone")
          ),
          renderErrors("phone", state.errors),
          <.div(
            ^.className := formStyles.checkboxWrapper.htmlClass,
            <.input(^.className := formStyles.checkboxItem.htmlClass,
                    ^.`type` := "checkbox",
                    ^.name := "terms"),
            <.label(^.className := formStyles.checkboxLabel.htmlClass,
                    ^.`for` := "terms",
                    I18n.t("details.adoptionForm.terms"))
          ),
          renderErrors("terms", state.errors),
          <.div(
            ^.className := formStyles.submitWrapper.htmlClass,
            <.button(^.className := formStyles.submitButton.htmlClass,
                     ^.`type` := "submit",
                     ^.onClick ==> adoptPet,
                     I18n.t("details.adoptionForm.submit"))
          )
        )
      )
    }
  }

  private val component = ScalaComponent
    .builder[AdoptPetProps]("AdoptPet")
    .initialState[AdoptPetState](AdoptPetState(Map.empty))
    .renderBackend[Backend]
    .build

  def apply(pet: Pet, router: RouterCtl[PetPages])
    : Unmounted[AdoptPetProps, AdoptPetState, Backend] =
    component(AdoptPetProps(pet, router))
}
