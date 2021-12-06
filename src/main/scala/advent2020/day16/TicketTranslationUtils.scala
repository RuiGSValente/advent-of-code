package advent2020.day16

object TicketTranslationUtils {

  case class minMax(min: Int, max: Int)

  case class DepartureFields(field: String, seq: Seq[minMax])

  case class Ticket(fieldIntervals: Seq[DepartureFields],
                    myNumbers: Seq[Int],
                    nearbyNumbers: Seq[Seq[Int]])

  //Part 1
  @scala.annotation.tailrec
  def accumulateNotValid(fieldIntervals: Seq[DepartureFields],
                         numberList: Seq[Int],
                         acc: Long): Long = {

    if (numberList.isEmpty) acc
    else {
      val booleanResult = fieldIntervals
        .flatMap(_.seq)
        .forall(
          threshold =>
            threshold.min >= numberList.head || threshold.max <= numberList.head
        )
      if (booleanResult)
        accumulateNotValid(
          fieldIntervals,
          numberList.tail,
          acc + numberList.head
        )
      else accumulateNotValid(fieldIntervals, numberList.tail, acc)
    }
  }

  //Part 2
  def splitTicket(listOfPath: Seq[String]): Ticket = {

    val (fieldRanges: Seq[String], other: Seq[String]) = listOfPath.splitAt(21)

    val fieldIntervals: Seq[DepartureFields] = fieldRanges
      .map { fieldRange =>
        val valuesWithHash: Seq[String] =
          fieldRange.split(':').tail.flatMap(_.split("or")).toSeq

        val fields: String = fieldRange.split(':').head.trim

        val seqOfMinMax: Seq[minMax] = valuesWithHash.map { y =>
          val minMaxInts: Seq[Int] = y.split("-").map(_.trim.toInt).toSeq

          minMax(minMaxInts.head, minMaxInts(1))
        }
        DepartureFields(fields, seqOfMinMax)
      }
      .filter(_.field.nonEmpty)

    val (myTicket: Seq[String], nearbyTickets: Seq[String]) = other.splitAt(3)

    val myNumbers: Seq[Int] = myTicket
      .mkString(" ")
      .split(",")
      .map(_.filter(_.isDigit))
      .filter(_.nonEmpty)
      .map(_.toInt)
      .toSeq

    val nearbyNumbers: Seq[Seq[Int]] = nearbyTickets
      .map(
        _.split(",").map(_.filter(_.isDigit)).filter(_.nonEmpty).map(_.toInt)
      )
      .filter(_.nonEmpty)
      .map(_.toSeq)

    Ticket(fieldIntervals, myNumbers, nearbyNumbers)
  }

  def tagNotValid(fieldIntervals: Seq[DepartureFields],
                  listValues: Seq[Seq[Int]]): Seq[Seq[Option[Int]]] = {
    listValues.map { values =>
      values.map { value =>
        val booleanResult: Boolean =
          fieldIntervals
            .flatMap(_.seq)
            .forall(
              thresholds => thresholds.min >= value || thresholds.max <= value
            )

        if (booleanResult) None
        else Some(value)
      }
    }
  }

  def filterSearchClass(
    fieldIntervals: Seq[DepartureFields],
    listValues: Seq[Seq[Option[Int]]]
  ): Seq[(String, Seq[Int])] = {

    val transposeValues: Seq[Seq[Option[Int]]] = listValues.transpose

    val booleanCondition: Seq[(String, Seq[Int])] = fieldIntervals.map {
      departureField =>
        val rowsFilter: Seq[Boolean] = transposeValues.map { values =>
          values.flatten.forall(
            value =>
              (departureField.seq.head.min <= value && departureField.seq.head.max >= value) ||
                (departureField.seq.tail.head.min <= value && departureField.seq.tail.head.max >= value)
          )
        }
        (departureField.field, rowsFilter.zipWithIndex.filter(_._1).map(_._2))
    }
    booleanCondition
  }

  @scala.annotation.tailrec
  def findColumns(listPossibleColumns: Seq[(String, Seq[Int])],
                  acc: Seq[(String, Int)]): Seq[Int] = {
    if (listPossibleColumns.isEmpty)
      acc.filter(_._1.contains("departure")).map(_._2)
    else {
      val (
        updatedAcc: Seq[(String, Seq[Int])],
        updatedList: Seq[(String, Seq[Int])]
      ) =
        listPossibleColumns.partition(_._2.length == 1)

      val columnFound: Seq[Int] = updatedAcc.map(_._2.head)

      findColumns(updatedList.map {
        case (str, seqInt)                          => (str, seqInt.diff(columnFound))
      }, acc ++ updatedAcc.map { case (str, seqInt) => (str, seqInt.head) })
    }
  }

}
