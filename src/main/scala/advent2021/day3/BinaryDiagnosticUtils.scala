package advent2021.day3

object BinaryDiagnosticUtils {

  def binaryMostCommon(input: List[List[Int]]): Seq[Int] = {
    input.transpose.map(_.groupBy(identity).maxBy(_._2.size)._1)
  }

  def binaryLessCommon(input: List[List[Int]]): Seq[Int] = {
    input.transpose.map(_.groupBy(identity).minBy(_._2.size)._1)
  }

  def C02ScrubberRating(input: List[List[Int]]): Seq[Int] = {
    if (input.length == 1) input.head
    else {
      val lessCommon: Int = Map(input.transpose.head.groupBy(identity).toSeq.sortWith(_._1 < _._1): _*).minBy(_._2.size)._1
      val newInput: List[List[Int]] = input.filter(_.head == lessCommon).map(_.tail)
      lessCommon +: C02ScrubberRating(newInput)
    }
  }

  def oxygenGeneratorRating(input: List[List[Int]]): Seq[Int] = {
    if (input.length == 1) input.head
    else {
      val lessCommon: Int = Map(input.transpose.head.groupBy(identity).toSeq.sortWith(_._1 > _._1): _*).maxBy(_._2.size)._1
      val newInput: List[List[Int]] = input.filter(_.head == lessCommon).map(_.tail)
      lessCommon +: oxygenGeneratorRating(newInput)
    }
  }

}
