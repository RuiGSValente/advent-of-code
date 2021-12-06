package advent2020.day16

import advent2020.day16.TicketTranslationUtils._
import utils.ReadUtils.readFromResources
object TicketTranslation extends App {

  val listOfPath: Seq[String] =
    readFromResources("advent2020/16_12_2020.txt")

  val ticket: Ticket = splitTicket(listOfPath)

  //PART 1
  println(
    accumulateNotValid(ticket.fieldIntervals, ticket.nearbyNumbers.flatten, 0)
  )

  //PART 2
  println(
    findColumns(
      filterSearchClass(
        ticket.fieldIntervals,
        tagNotValid(ticket.fieldIntervals, ticket.nearbyNumbers)
      ),
      Seq()
    ).foldLeft(1L)((acc, op) => acc * ticket.myNumbers(op))
  )
}
