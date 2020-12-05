package day5

import java.lang.Integer.parseInt

object BinaryBoardingUtils {

  def boardingToInt(seqBoarding: Seq[String]): Seq[(Int, Int)] = {
    seqBoarding.map { boardingString =>
      val (rowString: String, seatString: String) = boardingString.splitAt(7)

      val rowBinary: String = rowString.replace("F", "0").replace("B", "1")
      val seatBinary: String = seatString.replace("L", "0").replace("R", "1")

      val rowInt: Int = parseInt(rowBinary, 2)
      val seatInt: Int = parseInt(seatBinary, 2)

      (rowInt, seatInt)
    }
  }

  def calculateSeatID(seqBoarding: Seq[String]): Seq[Int] = {
    boardingToInt(seqBoarding).map {
      case (rowInt, seatInt) => rowInt * 8 + seatInt
    }
  }

  def calculateHighest(seqBoarding: Seq[String]): Int =
    calculateSeatID(seqBoarding).max

  def calculateSeat(seqBoarding: Seq[String]): Int = {
    val seqSeatIDs = calculateSeatID(seqBoarding)
    List.range(seqSeatIDs.min, seqSeatIDs.max).diff(seqSeatIDs).head
  }
}
