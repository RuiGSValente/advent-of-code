package advent2020.day11

object SeatingSystemUtils {

  //Part 1 and 2 Auxiliary
  def visiblesSeatCalculation(i: Int,
                              j: Int,
                              currentMap: Array[Array[Char]],
                              firsPart: Boolean): Int = {
    @scala.annotation.tailrec
    def searchUntil(iPosition: Int,
                    jPosition: Int,
                    iIncrement: Int,
                    jIncrement: Int): Option[Char] = {
      currentMap
        .lift(iPosition + iIncrement)
        .flatMap(_.lift(jPosition + jIncrement)) match {
        case Some(seatChar) =>
          if (seatChar == '.') {
            searchUntil(
              iPosition + iIncrement,
              jPosition + jIncrement,
              iIncrement,
              jIncrement
            )
          } else Some(seatChar)
        case None => None
      }
    }

    Seq((0, 1), (1, 0), (0, -1), (-1, 0), (1, 1), (-1, 1), (1, -1), (-1, -1))
      .flatMap {
        case (iIncrement, jIncrement) =>
          if (firsPart)
            currentMap.lift(i + iIncrement).flatMap(_.lift(j + jIncrement))
          else
            searchUntil(i, j, iIncrement, jIncrement)
      }
      .count(_ == '#')
  }

  //Main part 1 and 2
  @scala.annotation.tailrec
  def visibleSeatCalculator(oldMap: Array[Array[Char]],
                            thresholdFreeSeat: Int,
                            firstPart: Boolean): Array[Array[Char]] = {

    val newMap: Array[Array[Char]] = oldMap.map(_.clone)

    for (i <- oldMap.indices) {
      for (j <- oldMap.head.indices) {
        oldMap(i)(j) match {
          case 'L' =>
            if (visiblesSeatCalculation(i, j, oldMap, firstPart) == 0)
              newMap(i).update(j, '#')
            else 'L'
          case '#' =>
            if (visiblesSeatCalculation(i, j, oldMap, firstPart) >= thresholdFreeSeat)
              newMap(i).update(j, 'L')
            else '#'
          case otherChar => newMap(i)(j) = otherChar
        }
      }
    }

    if (newMap.zip(oldMap).forall { case (i, j) => i sameElements j }) {
      newMap
    } else {
      visibleSeatCalculator(newMap, thresholdFreeSeat, firstPart)
    }
  }
}
