package day11

import day11.SeatingSystemUtils._
import utils.ReadUtils._

object SeatingSystem extends App {

  val listOfPath: Array[Array[Char]] =
    readFromResources("11_12_2020.txt").map(_.toArray).toArray

  val resultOccupiedSeats: Int =
    adjacentSeatCalculator(listOfPath).map(_.toSeq.count(_ == '#')).toSeq.sum

  println("Result part 1 day 10: " + resultOccupiedSeats)

  val visibleOccupiedSeats =
    visibleSeatCalculator(listOfPath).map(_.toSeq.count(_ == '#')).toSeq.sum

  println("Result part 2 day 10: " + visibleOccupiedSeats)

}
