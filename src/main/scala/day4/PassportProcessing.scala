package day4

import day4.PassportProcessingUtils._
import utils.ReadUtils.readFromResources
object PassportProcessing extends App {

  val listOfPath: Seq[String] = readFromResources("04_12_2020.txt")

  println("Result part 1 day 4: " + splitFields(listOfPath))
  println(
    "Result part 2 day 4: " + checkPassportCondition(splitText(listOfPath))
  )

}
