package day11

object test extends App {

  val currentMap = Array(
    Array(1, 2, 3, 4, 5, 6, 7),
    Array(8, 9, 10, 11, 12, 13, 14),
    Array(15, 16, 17, 18, 19, 20, 21),
    Array(22, 23, 24, 25, 26, 27, 28),
    Array(29, 30, 31, 32, 33, 34, 35)
  )

  val i = 3
  val j = 3

  val columnsRange: Seq[Int] = currentMap.head.indices

  val rowRange: Seq[Int] = currentMap.indices

  //val horizontalE = ???
  //println(currentMap(i)(j))
  //println(horizontalO.find(_ != 24))

  val horizontalO = (1 to j).map(x => currentMap(i)(j - x))

  val horizontalE = (j + 1 to columnsRange.last).map(x => currentMap(i)(x))

  val verticalN = (1 to i).map(x => currentMap(i - x)(j))

  val verticalS = (i + 1 to rowRange.last).map(x => currentMap(x)(j))

  val diagonalNO = (1 to Seq(i, j).min).map(pos => currentMap(i - pos)(j - pos))

  val diagonalSE = (1 to Seq(rowRange.last - i, columnsRange.last - j).min)
    .map(pos => currentMap(i + pos)(j + pos))

  val diagonalSO = (1 to Seq(rowRange.last - i, j).min)
    .map(pos => currentMap(i + pos)(j - pos))

  val diagonalNE = (1 to Seq(i, columnsRange.last - j).min)
    .map(pos => currentMap(i - pos)(j + pos))

  /*
 */
}
