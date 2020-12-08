package day8

import day8.HandheldHaltingUtils._
import utils.ReadUtils._

object HandheldHalting extends App {

  val listOfPath: Seq[String] = readFromResources("08_12_2020.txt")

  val (indexesSeq: Seq[Int], accValue: Int) =
    accumulatorCalculator(splitTextToInstructions(listOfPath), Seq(0), 0)

  println("Result part 1 day 8: " + accValue)

  println(
    "Result part 2 day 8: " +
      accumulatorCalculator2(splitTextToInstructions(listOfPath), indexesSeq)
  )
}
