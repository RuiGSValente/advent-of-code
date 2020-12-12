package day12

import day12.RainRiskUtils._
import utils.ReadUtils._

object RainRisk extends App {

  val listOfPath: Seq[String] =
    readFromResources("12_12_2020.txt")

  val resultRainRisk: Int =
    RainRiskCalculator(splitTextToInstructions(listOfPath), 0, 0, 0, 1)

  println("Result part 1 day 12: " + resultRainRisk)

  val resultRainRiskWay: Int =
    RainRiskWayPointCalculator(splitTextToInstructions(listOfPath), 0, 0, 1, 10)

  println("Result part 1 day 12: " + resultRainRiskWay)

}
