package day2

import day2.PasswordPhilosophyUtils._
import utils.ReadUtils.readFromResources

object PasswordPhilosophy extends App {

  val listOfNumbers: Seq[String] = readFromResources("02_12_2020.txt")

  println("Result part 1 day 2: " + countValidPasswordPart1(listOfNumbers))
  println("Result part 2 day 2: " + countValidPasswordPart2(listOfNumbers))

}
