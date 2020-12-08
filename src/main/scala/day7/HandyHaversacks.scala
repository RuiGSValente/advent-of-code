package day7

import day7.HandyHaversacksUtils._
import utils.ReadUtils._

object HandyHaversacks extends App {

  val listOfPath: Seq[String] = readFromResources("07_12_2020.txt")

  println(
    "Result part 1 day 7: " +
      containsBagCalculatorRecursive(
        readBagsSpecifications(listOfPath),
        Seq("shiny gold"),
        Set()
      )
  )

  /*
  Accumulator value starts at -1 because the solution should not count the first shiny gold bag
   */
  println(
    "Result part 2 day 7: " +
      bagCalulator(
        readBagsSpecifications(listOfPath),
        Seq(("shiny gold", 1)),
        -1
      )
  )
}
