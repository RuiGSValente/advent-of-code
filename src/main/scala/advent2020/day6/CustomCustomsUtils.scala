package advent2020.day6

object CustomCustomsUtils {

  def countDistinctCharacters(seqStr: Seq[String]): Int = {
    seqStr.map(_.distinct.count(_.isLetter)).sum
  }

  def countCommonCharactersByGroup(seqStr: Seq[String]): Int = {
    seqStr.map { str =>
      val separated: Array[List[Char]] = str.split(" ").map(_.distinct.toList)

      separated
        .foldLeft(separated.head)((acc, listChar) => acc.intersect(listChar))
        .length
    }.sum
  }
}
