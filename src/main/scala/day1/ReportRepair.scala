package day1

import day1.ReportRepairUtils.{searchSumBetweenThree, searchSumBetweenTwo}
import utils.ReadUtils.readFromResources
object ReportRepair extends App {

  val listOfNumbers: Seq[Int] = readFromResources("01_12_2020.txt").map(_.toInt)

  searchSumBetweenTwo(listOfNumbers, value = 2020)
    .map(tuple => println("Result part 1 day 1 " + tuple._1 * tuple._2))

  searchSumBetweenThree(listOfNumbers, value = 2020)
    .map(
      triple =>
        println("Result part 2 day 1 " + triple._1 * triple._2 * triple._3)
    )

}
