package advent2021.day4

import utils.ReadUtils.readFromResources

import scala.util.matching.Regex

object GiantSquid extends App {

  val listOfNumbers: Seq[String] = readFromResources("advent2021/04_12_2021.txt")

  val bingoNumberCall: Seq[Int] = listOfNumbers.head.split(',').map(_.toInt).toSeq

  val bingoCards: Seq[Array[Array[Int]]] = listOfNumbers.tail.sliding(6).map {
    _.tail.map(_.split("\\D+").filter(_.nonEmpty).map(_.toInt)).toArray
  }.toSeq

}
