package org.make.workshop.data

import org.make.workshop.shared.Pet

import org.make.workshop.shared.Race.Lizard

object Lizards {
  val balthazar = Pet(
    id = "lizard-1",
    name = "Balthazar",
    race = Lizard,
    age = 4,
    picture =
      "https://images.pexels.com/photos/325946/pexels-photo-325946.jpeg?auto=compress&cs=tinysrgb&h=350",
    description = "He likes it hot and dry."
  )

}
