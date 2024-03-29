package advent2020.day4

import advent2020.day4.PassportProcessingUtils._
import utils.ReadUtils._
object PassportProcessing extends App {

  val listOfPath: Seq[String] = readFromResources("advent2020/04_12_2020.txt")

  println("Result part 1 day 4: " + splitFields(splitText(listOfPath)))
  println(
    "Result part 2 day 4: " + checkPassportCondition(splitText(listOfPath))
  )

}
