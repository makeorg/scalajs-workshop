package org.make.workshop.data

import org.make.workshop.shared.Pet

import org.make.workshop.shared.Race.Dog

object Dogs {
  val medor = Pet(
    id = "dog-1",
    name = "Medor",
    race = Dog,
    age = 14,
    picture =
      "https://www.la-spa.fr/sites/default/files/styles/fiche_animal_620x375/public/animals/314502.jpg?itok=7PkoeZ2h",
    description = "Cool dog looking forward to sleeping with you."
  )

  val annivia = Pet(
    id = "dog-2",
    name = "Annivia",
    race = Dog,
    age = 2,
    picture =
      "https://www.la-spa.fr/sites/default/files/styles/fiche_animal_620x375/public/animals/392149.jpg?itok=3NilnQ4C",
    description = "A bit shy but still a lovely dog."
  )

  val puppy = Pet(
    id = "dog-3",
    name = "Puppy",
    race = Dog,
    age = 0,
    picture =
      "https://www.la-spa.fr/sites/default/files/styles/fiche_animal_620x375/public/animals/392217_0.jpg?itok=Gl0Di2lH",
    description = "Happiness is a warm puppy."
  )
}
