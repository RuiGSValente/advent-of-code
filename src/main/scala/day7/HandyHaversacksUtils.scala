package day7

object HandyHaversacksUtils {

  case class Bag(quantity: Int, colour: String, bagCarried: Seq[Bag] = Seq())

  def splitText(str: String): Array[String] = {
    str
      .split("bag+(s?)")
      .map(
        _.replaceAll("[\\p{Punct}]", "")
          .replaceAll("contain", "")
          .replaceAll("no other", "")
          .trim
      )
      .filter(_.nonEmpty)
  }

  def readBagsSpecifications(seqStr: Seq[String]): Seq[Bag] = {
    seqStr.map { rules =>
      val split: Array[String] = splitText(rules)

      Bag(
        quantity = 1,
        colour = split.head.filterNot(_.isDigit),
        bagCarried = split.drop(1).flatMap { str =>
          for {
            someQuantity <- Some(str.filter(_.isDigit).trim).filter(_.nonEmpty)
            someColour <- Some(str.filterNot(_.isDigit).trim).filter(_.nonEmpty)
          } yield Bag(quantity = someQuantity.toInt, colour = someColour)
        }
      )
    }
  }

  @scala.annotation.tailrec
  def containsBagCalculatorRecursive(seqBags: Seq[Bag],
                                     colours: Seq[String],
                                     acc: Set[String]): Int = {

    val bagsContainColour: Seq[String] = seqBags
      .filter(_.bagCarried.map(_.colour).exists(colours.contains))
      .map(_.colour)

    if (bagsContainColour.nonEmpty) {
      containsBagCalculatorRecursive(
        seqBags,
        bagsContainColour,
        acc ++ bagsContainColour
      )
    } else {
      acc.size
    }
  }

  @scala.annotation.tailrec
  def bagCalculator(seqBags: Seq[Bag],
                    bagsInside: Seq[(String, Int)],
                    acc: Int): Int = {

    val bagContains: Seq[(String, Int)] =
      bagsInside.flatMap {
        case (bagInsideColor, bagInsideValue) =>
          seqBags
            .filter(_.colour == bagInsideColor)
            .flatMap(
              _.bagCarried.map(
                bagCarried =>
                  (bagCarried.colour, bagCarried.quantity * bagInsideValue)
              )
            )
      }

    val accUpdated: Int = acc + bagsInside.map(_._2).sum

    if (bagContains.nonEmpty)
      bagCalculator(seqBags, bagContains, accUpdated)
    else
      accUpdated

  }
}
