package org.make.workshop.data

import org.make.workshop.shared.Pet

import org.make.workshop.shared.Race.Unicorn

object Unicorns {
  val charlie = Pet(
    id = "unicorn-1",
    name = "Charlie",
    race = Unicorn,
    age = 521,
    picture =
      "https://vignette.wikia.nocookie.net/filmcow/images/d/de/Charlie_episode_4.png/revision/latest?cb=20170628143744",
    description = "They took my freakin' kidney !"
  )

}
