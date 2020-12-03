package day3

import day3.TobogganTrajectory.ConfigVals

object TobogganTrajectoryUtils {

  @scala.annotation.tailrec
  def splitString(listString: Seq[String],
                  position: Int,
                  numberOfTrees: Int,
                  config: ConfigVals): Int = {

    val nextListOfStrings: Seq[String] =
      listString.takeRight(listString.length - config.downMoves)

    val nextPosition: Int = (position + config.rightMoves) % config.sizeString

    if (listString.nonEmpty)
      listString.head(position) match {
        case '#' =>
          splitString(
            nextListOfStrings,
            nextPosition,
            numberOfTrees + 1,
            config
          )
        case _ =>
          splitString(nextListOfStrings, nextPosition, numberOfTrees, config)
      } else numberOfTrees
  }

}
