package org.make.workshop.data

import org.make.workshop.shared.Pet

import org.make.workshop.shared.Race.Parrot

object Parrots {
  val coco = Pet(
    id = "parrot-1",
    name = "Coco",
    race = Parrot,
    age = 2,
    picture = "https://pbs.twimg.com/profile_images/1558791113/3.PNG",
    description = "Coco ! Hello. Shhhht. Repeat !!"
  )

  val jack = Pet(
    id = "parrot-2",
    name = "Jack",
    race = Parrot,
    age = 6,
    picture = "https://i.ytimg.com/vi/lBzt-ELYdv8/maxresdefault.jpg",
    description = "Old one-eyed pirates' parrot."
  )
}
