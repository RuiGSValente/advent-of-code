package day14

import java.lang.Long.parseLong

object DockingDataUtils {

  case class Data(mask: String, values: Seq[(Int, Int)])

  def splitDockingData(seq: Seq[String]): Seq[Data] =
    seq
      .mkString(" ")
      .split("mask = ")
      .filter(_.nonEmpty)
      .map { str =>
        val split: Array[String] = str.split("mem").filter(_.nonEmpty)

        val values: Seq[(Int, Int)] = split.tail.map { y =>
          val filterNumbers: Array[String] = y
            .filter(x => x.isDigit || x.isWhitespace)
            .split(" ")
            .filter(_.nonEmpty)

          (filterNumbers.head.toInt, filterNumbers(1).toInt)
        }.toSeq

        Data(split.head.trim, values)
      }
      .toSeq

  @scala.annotation.tailrec
  def applyMask(binaryMask: String,
                binaryValue: String,
                newValue: String): String = {

    if (binaryMask.length == 0) newValue
    else {
      val newMaskChr: String = binaryMask.takeRight(1)

      val newValueAcc: String =
        if (newMaskChr != "X") newMaskChr
        else binaryValue.takeRight(1)

      applyMask(
        binaryMask.dropRight(1),
        binaryValue.dropRight(1),
        newValueAcc ++ newValue
      )
    }
  }

  def maskData(data: Data): Map[Int, Long] = {

    val dataMask: String = data.mask

    data.values.map { x =>
      val valueBinary: String = x._2.toBinaryString

      val valueBinarySized
        : String = "0" * (dataMask.length - valueBinary.length) + valueBinary

      (x._1, parseLong(applyMask(dataMask, valueBinarySized, ""), 2))
    }
  }.toMap

  def seqMaskData(seqData: Seq[Data]): Long = {
    seqData
      .foldLeft(Map(): Map[Int, Long])(
        (accMap, newMap) => accMap ++ maskData(newMap)
      )
      .filter(_._2 != 0)
      .values
      .sum

  }

  //Part 2
  @scala.annotation.tailrec
  def applyMaskTwo(binaryMask: String,
                   binaryValue: String,
                   newValue: String): String = {

    if (binaryMask.length == 0) newValue
    else {
      val newMaskChr: String = binaryMask.takeRight(1)

      val newValueAcc: String =
        if (newMaskChr == "X" | newMaskChr == "1") newMaskChr
        else binaryValue.takeRight(1)

      applyMaskTwo(
        binaryMask.dropRight(1),
        binaryValue.dropRight(1),
        newValueAcc ++ newValue
      )
    }
  }

  def applyXAlternatives(binarySeqString: Seq[String]): Seq[String] = {

    binarySeqString.flatMap { binaryString =>
      val optionChar: Int = binaryString.indexOf('X')
      optionChar match {
        case -1 => Seq(binaryString)
        case position =>
          applyXAlternatives(
            Seq(
              binaryString.updated(position, '1'),
              binaryString.updated(position, '0')
            )
          )
      }
    }
  }

  def maskDataTwo(data: Data): Map[Long, Long] = {

    val dataMask: String = data.mask

    data.values.flatMap {
      case (mem, value) =>
        val valueBinary: String = mem.toBinaryString

        val valueBinarySized: String =
          "0" * (dataMask.length - valueBinary.length) + valueBinary

        val applyTheMask: String = applyMaskTwo(dataMask, valueBinarySized, "")

        val searchForCorrespondences: Seq[String] =
          applyXAlternatives(Seq(applyTheMask))

        searchForCorrespondences.map(y => (parseLong(y, 2), value.toLong))
    }
  }.toMap

  def seqMaskDataTwo(seqData: Seq[Data]): Long = {
    seqData
      .foldLeft(Map(): Map[Long, Long])(
        (accMap, newMap) => accMap ++ maskDataTwo(newMap)
      )
      .values
      .sum
  }

}
