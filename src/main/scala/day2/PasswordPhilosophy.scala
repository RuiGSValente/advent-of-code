package day2

import day2.PasswordPhilosophyUtils._
import utils.ReadUtils.readFromResources

object PasswordPhilosophy extends App {

  val listOfNumbers: Seq[String] = readFromResources("02_12_2020.txt")

  println(countValidPasswordPart1(listOfNumbers))
  println(countValidPasswordPart2(listOfNumbers))

}
