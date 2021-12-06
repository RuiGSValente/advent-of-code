package advent2020.day3

import advent2020.day3.TobogganTrajectoryUtils.splitString
import utils.ReadUtils.readFromResources

object TobogganTrajectory extends App {

  val listOfPath: Seq[String] = readFromResources("advent2020/03_12_2020.txt")

  case class ConfigVals(rightMoves: Int, downMoves: Int, sizeString: Int)

  val sizeStringOfPath: Int = listOfPath.head.length

  /*
    Right 1, down 1.
    Right 3, down 1. (This is the slope you already checked.)
    Right 5, down 1.
    Right 7, down 1.
    Right 1, down 2.
   */
  val right1Down1: Long =
    splitString(listOfPath, 0, 0, ConfigVals(1, 1, sizeStringOfPath))
  val right3Down1: Long =
    splitString(listOfPath, 0, 0, ConfigVals(3, 1, sizeStringOfPath))
  val right5Down1: Long =
    splitString(listOfPath, 0, 0, ConfigVals(5, 1, sizeStringOfPath))
  val right7Down1: Long =
    splitString(listOfPath, 0, 0, ConfigVals(7, 1, sizeStringOfPath))
  val right1Down2: Long =
    splitString(listOfPath, 0, 0, ConfigVals(1, 2, sizeStringOfPath))

  println("Result part 1 day 3: " + right3Down1)
  println(
    "Result part 2 day 3: " + right1Down1 * right3Down1 * right5Down1 * right7Down1 * right1Down2
  )

}
