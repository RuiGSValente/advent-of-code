package advent2020.day1

import advent2020.day1.ReportRepairUtils.{searchSumBetweenThree, searchSumBetweenTwo}
import utils.ReadUtils.readFromResources
object ReportRepair extends App {

  val listOfNumbers: Seq[Int] = readFromResources("advent2020/01_12_2020.txt").map(_.toInt)

  searchSumBetweenTwo(listOfNumbers, value = 2020)
    .foreach(tuple => println("Result part 1 day 1: " + tuple._1 * tuple._2))

  searchSumBetweenThree(listOfNumbers, value = 2020)
    .foreach(
      triple =>
        println("Result part 2 day 1: " + triple._1 * triple._2 * triple._3)
    )

}
