package day9

import day9.EncodingErrorUtils._
import utils.ReadUtils._

object EncodingError extends App {

  val listOfPath: Seq[Long] = readFromResources("09_12_2020.txt").map(_.toLong)

  val verificationLong: Option[Long] = verification(listOfPath, 25)

  val sumListLong: Option[Long] = verificationLong.flatMap(
    long => sumList(listOfPath.take(listOfPath.indexOf(long)), long)
  )

  println("Result part 1 day 9: " + verificationLong)

  println("Result part 2 day 9: " + sumListLong)
}
