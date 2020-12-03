package day3

import day3.TobogganTrajectory.ConfigVals

object TobogganTrajectoryUtils {

  @scala.annotation.tailrec
  def splitString(listString: Seq[String],
                  position: Int,
                  numberOfTrees: Int,
                  config: ConfigVals): Int = {

    if (listString.nonEmpty)
      listString.head(position) match {
        case '#' =>
          splitString(
            listString.takeRight(listString.length - config.downMoves),
            (position + config.rightMoves) % config.sizeString,
            numberOfTrees + 1,
            config
          )
        case _ =>
          splitString(
            listString.takeRight(listString.length - config.downMoves),
            (position + config.rightMoves) % config.sizeString,
            numberOfTrees,
            config
          )
      } else numberOfTrees
  }

}
