package advent2020.day13

object ShuttleSearchUtils {

  case class ShuttleParameters(timestamp: Int, busIds: Seq[(Long, Long)])

  def splitShuttle(list: Seq[String]): ShuttleParameters = {

    val timestamp: Int = list.head.toInt

    val busIds: Seq[(Long, Long)] =
      list(1)
        .split(",")
        .map(number => (number, list(1).split(",").indexOf(number)))
        .filterNot(_._1 == "x")
        .map { case (number, pos) => (number.toLong, pos.toLong) }
        .toSeq

    ShuttleParameters(timestamp, busIds)
  }

  def calculateNextShuffle(shuttleParameter: ShuttleParameters): Long = {

    val minTuple: (Long, Long) = shuttleParameter.busIds
      .map {
        case (timeInterval, _) =>
          (
            timeInterval - (shuttleParameter.timestamp % timeInterval),
            timeInterval
          )
      }
      .minBy(_._1)

    minTuple._1 * minTuple._2
  }

  @scala.annotation.tailrec
  def calculateNemSeiBemOQue(seqBusIds: Seq[(Long, Long)],
                             accumulator: Long,
                             multiplication: Long): Long = {

    if (seqBusIds.length > 1) {

      val evaluateSeq: Boolean = seqBusIds
        .take(2)
        .map {
          case (number, firstStep) => (accumulator + firstStep) % number == 0L
        }
        .forall(_ == true)

      if (evaluateSeq) {
        val newMultiplication: Long = multiplication * seqBusIds.head._1

        calculateNemSeiBemOQue(seqBusIds.tail, accumulator, newMultiplication)
      } else {
        val newAccumulator: Long = accumulator + multiplication

        calculateNemSeiBemOQue(seqBusIds, newAccumulator, multiplication)
      }
    } else accumulator
  }

}
