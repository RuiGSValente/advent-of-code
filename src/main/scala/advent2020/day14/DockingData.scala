package advent2020.day14

import advent2020.day14.DockingDataUtils._
import utils.ReadUtils.readFromResources

object DockingData extends App {

  val listOfPath: Seq[String] =
    readFromResources("advent2020/14_12_2020.txt")

  println("Result part 1 day 14: " + seqMaskData(splitDockingData(listOfPath)))
  println(
    "Result part 2 day 14: " + seqMaskDataTwo(splitDockingData(listOfPath))
  )

}
