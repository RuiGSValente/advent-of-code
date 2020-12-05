package day5

import day5.BinaryBoardingUtils._
import utils.ReadUtils.readFromResources

object BinaryBoarding extends App {

  val listOfPath: Seq[String] = readFromResources("05_12_2020.txt")

  println("Result part 1 day 5: " + calculateHighest(listOfPath))

  println("Result part 2 day 5: " + calculateSeat(listOfPath))

}
