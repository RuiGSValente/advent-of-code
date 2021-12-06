package advent2020.day13

import advent2020.day13.ShuttleSearchUtils._
import utils.ReadUtils._

object ShuttleSearch extends App {

  val listOfPath: Seq[String] =
    readFromResources("advent2020/13_12_2020.txt")

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
