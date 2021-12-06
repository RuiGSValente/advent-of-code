package advent2020.day6

import advent2020.day6.CustomCustomsUtils._
import utils.ReadUtils._

object CustomCustoms extends App {

  val listOfPath: Seq[String] = readFromResources("advent2020/06_12_2020.txt")

  println(
    "Result part 1 day 6: " + countDistinctCharacters(splitText(listOfPath))
  )

  println(
    "Result part 2 day 6: " + countCommonCharactersByGroup(
      splitText(listOfPath)
    )
  )

}
