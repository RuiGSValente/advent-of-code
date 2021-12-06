package advent2021.day2

import advent2021.day2.DiveUtils._
import utils.ReadUtils.readFromResources

object Dive extends App {

  val listOfNumbers: Seq[String] = readFromResources("advent2021/02_12_2021.txt")

  val resultDive: (Int, Int) = diveDive(listOfNumbers)

  println("Result part 1 day 1: " + (resultDive._1 * resultDive._2))

  val resultDiveAim: (Int, Int, Int) = diveAimDive(listOfNumbers)

  println("Result part 1 day 1: " + (resultDiveAim._1 * resultDiveAim._2))

}
