package day10

object AdapterArrayUtils {

  @scala.annotation.tailrec
  def subtractToNext(seqInt: Seq[Int], seqDifferences: Seq[Int]): Seq[Int] = {
    if (seqInt.size > 1) {
      subtractToNext(seqInt.tail, seqDifferences :+ seqInt(1) - seqInt.head)
    } else {
      seqDifferences
    }
  }

  def sortAndSubtract(seqInt: Seq[Int]): Map[Int, Int] = {
    subtractToNext(seqInt.sorted, Seq())
      .groupBy(identity)
      .view
      .mapValues(_.size)
      .toMap
  }

  @scala.annotation.tailrec
  def mapRecursive(seqSortedInt: Seq[(Int, Long)]): Long = {

    if (seqSortedInt.tail.isEmpty) seqSortedInt.head._2
    else {
      val (minorValue: Int, minorValueCount: Long) = seqSortedInt.head
      val addValueToFiltered: Seq[(Int, Long)] = seqSortedInt
        .filter { case (value, _) => (value - minorValue) <= 3 }
        .map { case (valueFiltered, _) => (valueFiltered, minorValueCount) }

      val updatedSequenceWithoutFirst: Seq[(Int, Long)] =
        (seqSortedInt ++ addValueToFiltered)
          .groupBy(_._1)
          .map { case (value, count) => value -> count.map(_._2).sum }
          .toSeq
          .sorted
          .tail

      mapRecursive(updatedSequenceWithoutFirst)
    }
  }
}
