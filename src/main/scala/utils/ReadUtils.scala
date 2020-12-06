package utils

import scala.io.Source.fromResource

object ReadUtils {

  def readFromResources(resource: String): Seq[String] =
    fromResource(resource).getLines.toSeq

  /*
  This function separates a sequence of strings by blank line
   */
  def splitText(seqStr: Seq[String]): Seq[String] = {

    @scala.annotation.tailrec
    def splitBlank(accStr: Seq[String],
                   seqStr: Seq[String],
                   accSeq: Seq[String]): Seq[String] = {

      seqStr match {
        case _ if seqStr.isEmpty            => accSeq
        case _ if !seqStr.exists(_.isBlank) => accSeq :+ seqStr.mkString(" ")
        case str if str.head.isBlank =>
          splitBlank(Seq(), seqStr.tail, accSeq :+ accStr.mkString(" "))
        case _ => splitBlank(accStr :+ seqStr.head, seqStr.tail, accSeq)
      }
    }

    splitBlank(Seq(), seqStr, Seq())
  }
}
