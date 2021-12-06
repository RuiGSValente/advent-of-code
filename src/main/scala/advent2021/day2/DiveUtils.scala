package advent2021.day2

import scala.annotation.tailrec

object DiveUtils {

  @tailrec
  def diveCalculation(input: Seq[(String, Int)], position: (Int, Int)): (Int, Int) = {
    if (input.isEmpty) position
    else {
      val newPosition: (Int, Int) = input match {
        case input if input.head._1 == "forward" => (position._1 + input.head._2, position._2)
        case input if input.head._1 == "up" => (position._1, position._2 + input.head._2)
        case input if input.head._1 == "down" => (position._1, position._2 - input.head._2)
        case _ => (position._1, position._2)
      }
      diveCalculation(input.tail, newPosition)
    }
  }

  def separateFields(input: Seq[String]): Seq[(String, Int)] = {
    input.map{row =>
      val fields: Seq[String] = row.split(' ').toSeq
      (fields.head, fields.tail.head.toInt)
    }
  }

  def diveDive(input: Seq[String]): (Int, Int) = {
    diveCalculation(separateFields(input), (0, 0))
  }

  @tailrec
  def diveAimCalculation(input: Seq[(String, Int)], position: (Int, Int, Int)): (Int, Int, Int) = {
    if (input.isEmpty) position
    else {
      val newPosition: (Int, Int, Int) = input match {
        case input if input.head._1 == "forward" => (position._1 + input.head._2, position._2 + (position._3*input.head._2), position._3)
        case input if input.head._1 == "up" => (position._1, position._2,  position._3 - input.head._2)
        case input if input.head._1 == "down" => (position._1, position._2, position._3 + input.head._2)
        case _ => (position._1, position._2, position._3)
      }
      diveAimCalculation(input.tail, newPosition)
    }
  }

  def diveAimDive(input: Seq[String]): (Int, Int, Int) = {
    diveAimCalculation(separateFields(input), (0, 0, 0))
  }
}
