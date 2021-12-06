package advent2020.day11

import advent2020.day11.SeatingSystemUtils._
import utils.ReadUtils._

object SeatingSystem extends App {

  val listOfPath: Array[Array[Char]] =
    readFromResources("advent2020/11_12_2020.txt").map(_.toArray).toArray

  val resultOccupiedSeats: Int =
    visibleSeatCalculator(listOfPath, 4, firstPart = true)
      .map(_.toSeq.count(_ == '#'))
      .toSeq
      .sum

  println("Result part 1 day 11: " + resultOccupiedSeats)

  val visibleOccupiedSeats =
    visibleSeatCalculator(listOfPath, 5, firstPart = false)
      .map(_.toSeq.count(_ == '#'))
      .toSeq
      .sum

  println("Result part 2 day 11: " + visibleOccupiedSeats)

}
