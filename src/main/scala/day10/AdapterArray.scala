package day10

import day10.AdapterArrayUtils._
import utils.ReadUtils._

object AdapterArray extends App {

  val listOfPath: Seq[Int] = readFromResources("10_12_2020.txt").map(_.toInt)

  val listOfSubtractions: Map[Int, Int] = sortAndSubtract(listOfPath)

  val resultSubtractions: Int = listOfSubtractions
    .get(1)
    .map(_ + 1)
    .getOrElse(0) * listOfSubtractions.get(3).map(_ + 1).getOrElse(0)

  println("Result part 1 day 9: " + resultSubtractions)

  val listTuple: Seq[(Int, Long)] = (0, 1L) +: listOfPath.sorted.map { x =>
    (x, 0L)
  }

  println("Result part 2 day 9: " + mapRecursive(listTuple))
}
