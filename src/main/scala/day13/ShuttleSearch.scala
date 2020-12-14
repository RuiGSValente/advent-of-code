package day13

import day13.ShutteSearchUtils._
import utils.ReadUtils._

object ShuttleSearch extends App {

  val listOfPath: Seq[String] =
    readFromResources("13_12_2020.txt")

  println(
    "Result part 1 day 13: " + calculateNextShuffle(splitShuttle(listOfPath))
  )

  println(
    "Result part 2 day 13: " + calculateNemSeiBemOQue(
      splitShuttle(listOfPath).busIds,
      0,
      1L
    )
  )

}
