package day9

object EncodingErrorUtils {

  @scala.annotation.tailrec
  def verification(seqInt: Seq[Long], interval: Int): Option[Long] = {

    if (seqInt.length > interval) {

      val newVal: Long = seqInt(interval)

      val preambleVerification: Boolean =
        seqInt.take(interval).combinations(2).toSeq.map(_.sum).contains(newVal)

      if (preambleVerification)
        verification(seqInt.drop(1), interval)
      else
        Some(newVal)

    } else None
  }

  @scala.annotation.tailrec
  def sumList(seqLong: Seq[Long], sumNumber: Long): Option[Long] = {

    val resultFind: Option[Seq[Long]] =
      (2 to seqLong.length)
        .map(number => seqLong.take(number))
        .find(_.sum == sumNumber)

    resultFind match {
      case _ if seqLong.length == 1 => None
      case Some(seqLong)            => Some(seqLong.max + seqLong.min)
      case None                     => sumList(seqLong.drop(1), sumNumber)
    }
  }
}
