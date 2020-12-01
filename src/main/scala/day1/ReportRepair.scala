package day1

import day1.ReportRepairUtils.{searchSumBetweenThree, searchSumBetweenTwo}
import utils.ReadUtils.readFromResources
object ReportRepair extends App {

  val listOfNumbers: Seq[Int] = readFromResources("01_12_2020.txt").map(_.toInt)

  println(
    searchSumBetweenTwo(listOfNumbers, value = 2020)
      .map(tuple => tuple._1 * tuple._2)
  )

  println(
    searchSumBetweenThree(listOfNumbers, value = 2020)
      .map(triple => triple._1 * triple._2 * triple._3)
  )

}
