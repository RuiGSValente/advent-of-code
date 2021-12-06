package advent2021.day3

import advent2021.day3.BinaryDiagnosticUtils._
import utils.ReadUtils.readFromResources

import java.lang.Integer.parseInt

object BinaryDiagnostic extends App {

  val listOfNumbers: List[List[Int]] = readFromResources("advent2021/03_12_2021.txt").map(_.toList.map(_.asDigit)).toList

  val resultBinaryConsumption: Int = parseInt(binaryMostCommon(listOfNumbers).mkString, 2) * parseInt(binaryLessCommon(listOfNumbers).mkString, 2)
  val resultBinaryRating: Int = parseInt(C02ScrubberRating(listOfNumbers).mkString, 2) * parseInt(oxygenGeneratorRating(listOfNumbers).mkString, 2)

  println("Result part 1 day 3: " + resultBinaryConsumption)
  println("Result part 2 day 3: " + resultBinaryRating)

}
