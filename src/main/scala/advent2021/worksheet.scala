package advent2021

object worksheet extends App{

  def C02ScrubberRating(input: List[List[Int]]): Seq[Int] = {
    if (input.length == 1) input.head
    else {
      val lessCommon: Int = Map(input.transpose.head.groupBy(identity).toSeq.sortWith(_._1 < _._1):_*).minBy(_._2.size)._1
      val newInput: List[List[Int]] = input.filter(_.head == lessCommon)
      (lessCommon +: C02ScrubberRating(newInput.map(_.tail)))
    }
  }


  def oxygenGeneratorRating(input: List[List[Int]]): Seq[Int] = {
    if (input.length == 1) input.head
    else {
      val lessCommon: Int = Map(input.transpose.head.groupBy(identity).toSeq.sortWith(_._1 > _._1):_*).maxBy(_._2.size)._1
      val newInput: List[List[Int]] = input.filter(_.head == lessCommon)
      (lessCommon +: oxygenGeneratorRating(newInput.map(_.tail)))
    }
  }

  val a: Array[Array[Int]] = Array(Array(0,0,1,0,0),
    Array(1,1,1,1,0),
    Array(1,0,1,1,0),
    Array(1,0,1,1,1),
    Array(1,0,1,0,1),
    Array(0,1,1,1,1),
    Array(0,0,1,1,1),
    Array(1,1,1,0,0),
    Array(1,0,0,0,0),
    Array(1,1,0,0,1),
    Array(0,0,0,1,0),
    Array(0,1,0,1,0))


  def getCol(n: Int, a: Array[Array[Int]]) = a.map{_(n - 1)}

  println(getCol(2, a).mkString)
                      //1011

}
