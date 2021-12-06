package advent2020.day1

object ReportRepairUtils {

  def searchSumBetweenTwo(listInt: Seq[Int], value: Int): Option[(Int, Int)] = {
    listInt match {
      case headSeq :: tailSeq =>
        tailSeq
          .find(number => headSeq + number == value)
          .map(numberFound => Some(headSeq, numberFound))
          .getOrElse(searchSumBetweenTwo(tailSeq, value))
      case _ => None
    }
  }

  def searchSumBetweenThree(listInt: Seq[Int],
                            value: Int): Option[(Int, Int, Int)] = {
    listInt match {
      case headSeq :: headTailSeq :: tailSeq =>
        searchSumBetweenTwo(headTailSeq +: tailSeq, value - headSeq)
          .map(numberFound => Some(headSeq, numberFound._1, numberFound._2))
          .getOrElse(searchSumBetweenThree(headTailSeq +: tailSeq, value))
      case _ => None
    }
  }

}
