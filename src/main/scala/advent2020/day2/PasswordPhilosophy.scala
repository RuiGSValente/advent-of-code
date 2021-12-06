package advent2020.day2

import advent2020.day2.PasswordPhilosophyUtils._
import utils.ReadUtils.readFromResources

object PasswordPhilosophy extends App {

  val listOfNumbers: Seq[String] = readFromResources("advent2020/02_12_2020.txt")

  println("Result part 1 day 2: " + countValidPasswordPart1(listOfNumbers))
  println("Result part 2 day 2: " + countValidPasswordPart2(listOfNumbers))

}
