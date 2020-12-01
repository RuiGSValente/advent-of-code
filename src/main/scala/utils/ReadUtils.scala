package utils

import scala.io.Source.fromResource

object ReadUtils {

  def readFromResources(resource: String): Seq[String] =
    fromResource(resource).getLines.toSeq

}
