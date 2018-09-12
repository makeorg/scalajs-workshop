package org.make.workshop.data

import org.make.workshop.shared.Pet
import org.make.workshop.shared.Race.Cat

object Cats {
  val felix = Pet(
    id = "cat-1",
    name = "Felix",
    race = Cat,
    age = 6,
    picture = "https://purr.objects-us-west-1.dream.io/i/o4bq0Nx.jpg",
    description = "Kitty cat with a cool description"
  )

  val minouchette = Pet(
    id = "cat-2",
    name = "Minouchette",
    race = Cat,
    age = 2,
    picture = "https://i.servimg.com/u/f80/11/00/97/51/minouc10.jpg",
    description = "Awwwwwww"
  )

  val norminet = Pet(
    id = "cat-42",
    name = "Norminet",
    race = Cat,
    age = 42,
    picture =
      "https://pbs.twimg.com/profile_images/837333455281221633/xOKfuwOl_400x400.jpg",
    description = "The answer."
  )

}
