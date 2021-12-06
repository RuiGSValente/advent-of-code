package advent2021.day1

import advent2021.day1.SonarSweepUtils.{increaseCount, increaseWindowCount}
import utils.ReadUtils.readFromResources

object SonarSweep extends App {

  val listOfNumbers: Seq[Int] = readFromResources("advent2021/01_12_2021.txt").map(_.toInt)

  println("Result part 1 day 1: " + increaseCount(listOfNumbers, count = 0))

  println("Result part 2 day 1: " + increaseWindowCount(listOfNumbers, count = 0))
}
