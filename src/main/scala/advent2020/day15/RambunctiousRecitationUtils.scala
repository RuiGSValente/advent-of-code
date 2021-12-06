package advent2020.day15

object RambunctiousRecitationUtils {

  @scala.annotation.tailrec
  def memoryGameMap(mapNumbers: Map[Int, Int],
                    lastValue: Int,
                    length: Int,
                    threshold: Int): Int = {

    if (length == threshold) lastValue
    else {
      val lastWithoutLast: Option[Int] = mapNumbers.get(lastValue)
      val newMap: Map[Int, Int] = mapNumbers ++ Map(lastValue -> length)

      if (lastWithoutLast.isEmpty)
        memoryGameMap(newMap, 0, length + 1, threshold)
      else
        memoryGameMap(
          newMap,
          length - lastWithoutLast.get,
          length + 1,
          threshold
        )
    }
  }
}
