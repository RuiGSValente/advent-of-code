package day11

import day11.test.{columnsRange, currentMap, i, j, rowRange}

object SeatingSystemUtils {

  //Part 1
  def adjacentSeq(i: Int, j: Int, currentMap: Array[Array[Char]]): Seq[Char] = {
    Seq(
      (i - 1, j - 1),
      (i - 1, j),
      (i - 1, j + 1),
      (i, j - 1),
      (i, j + 1),
      (i + 1, j - 1),
      (i + 1, j),
      (i + 1, j + 1)
    ).flatMap {
      case (row, col) => currentMap.lift(row).flatMap(_.lift(col))
    }
  }

  @scala.annotation.tailrec
  def adjacentSeatCalculator(oldMap: Array[Array[Char]]): Array[Array[Char]] = {

    val newMap: Array[Array[Char]] = oldMap.map(_.clone)

    for (i <- oldMap.indices) {
      for (j <- oldMap.head.indices) {
        oldMap(i)(j) match {
          case 'L' =>
            if (adjacentSeq(i, j, oldMap).count(_ == '#') == 0)
              newMap(i).update(j, '#')
            //Maybe this can be deleted
            else 'L'
          case '#' =>
            if (adjacentSeq(i, j, oldMap).count(_ == '#') >= 4)
              newMap(i).update(j, 'L')
            //Maybe this can be deleted
            else '#'
          case x => newMap(i)(j) = x
        }
      }
    }

    if (newMap.zip(oldMap).forall { case (i, j) => i sameElements j }) {
      newMap
    } else {
      adjacentSeatCalculator(newMap)
    }
  }

  //Part 2

  //def searchUntil(i: Int, j: Int, iIncrement, jIncrement, currentMap) = {

  //}
  def visibleSeq(i: Int, j: Int, currentMap: Array[Array[Char]]): Int = {

    val columnsRange: Seq[Int] = currentMap.head.indices

    val rowRange: Seq[Int] = currentMap.indices

    val horizontalO = (1 to j).map(x => currentMap(i)(j - x))

    val horizontalE = (j + 1 to columnsRange.last).map(x => currentMap(i)(x))

    val verticalN = (1 to i).map(x => currentMap(i - x)(j))

    val verticalS = (i + 1 to rowRange.last).map(x => currentMap(x)(j))

    val diagonalNO =
      (1 to Seq(i, j).min).map(pos => currentMap(i - pos)(j - pos))

    val diagonalSE = (1 to Seq(rowRange.last - i, columnsRange.last - j).min)
      .map(pos => currentMap(i + pos)(j + pos))

    val diagonalSO = (1 to Seq(rowRange.last - i, j).min)
      .map(pos => currentMap(i + pos)(j - pos))

    val diagonalNE = (1 to Seq(i, columnsRange.last - j).min)
      .map(pos => currentMap(i - pos)(j + pos))

    Seq(
      horizontalE,
      horizontalO,
      verticalN,
      verticalS,
      diagonalNE,
      diagonalSO,
      diagonalSE,
      diagonalNO
    ).flatMap(_.find(_ != '.')).count(_ == '#')

  }

  def visibleSeatCalculator(oldMap: Array[Array[Char]]): Array[Array[Char]] = {

    val newMap: Array[Array[Char]] = oldMap.map(_.clone)

    for (i <- oldMap.indices) {
      for (j <- oldMap.head.indices) {
        oldMap(i)(j) match {
          case 'L' =>
            if (visibleSeq(i, j, oldMap) == 0)
              newMap(i).update(j, '#')
            //Maybe this can be deleted
            else 'L'
          case '#' =>
            if (visibleSeq(i, j, oldMap) >= 5)
              newMap(i).update(j, 'L')
            //Maybe this can be deleted
            else '#'
          case x => newMap(i)(j) = x
        }
      }
    }

    if (newMap.zip(oldMap).forall { case (i, j) => i sameElements j }) {
      newMap
    } else {
      visibleSeatCalculator(newMap)
    }
  }

}
