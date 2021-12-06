package advent2021.day1

import scala.annotation.tailrec

object SonarSweepUtils {

  @tailrec
  def increaseCount(input: Seq[Int], count: Int): Int = {
    if (input.length <= 1) count
    else if (input.head < input.tail.head) increaseCount(input.tail, count + 1)
    else increaseCount(input.tail, count)
  }

  @tailrec
  def increaseWindowCount(input: Seq[Int], count: Int): Int = {
    if (input.length <= 3) count
      else if (input.take(3).sum < input.tail.take(3).sum) increaseWindowCount(input.tail, count + 1)
    else increaseWindowCount(input.tail, count)
  }
}
