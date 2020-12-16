package day15

import day15.RambunctiousRecitationUtils.memoryGameMap
import utils.ReadUtils.readFromResources

object RambunctiousRecitation extends App {

  val listOfPath: Seq[Int] =
    readFromResources("15_12_2020.txt").head.split(",").map(_.toInt)

  val mapListOfPath: Map[Int, Int] = listOfPath
    .dropRight(1)
    .map(value => (value, listOfPath.indexOf(value) + 1))
    .toMap

  println(
    "Result part 1 day 15: " + memoryGameMap(
      mapListOfPath,
      listOfPath.last,
      listOfPath.length,
      2020
    )
  )
  println(
    "Result part 2 day 15: " + memoryGameMap(
      mapListOfPath,
      listOfPath.last,
      listOfPath.length,
      30000000
    )
  )
}
