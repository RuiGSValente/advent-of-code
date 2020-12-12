package day12

import utils.MathUtils.trigNewPosition

object RainRiskUtils {

  case class Instruction(direction: Char, argument: Int)

  def splitTextToInstructions(seqStr: Seq[String]): Seq[Instruction] = {
    seqStr.flatMap { str =>
      str
        .find(_.isLetter)
        .map(char => Instruction(char, str.filter(_.isDigit).toInt))
    }
  }

  //Part 1
  @scala.annotation.tailrec
  def RainRiskCalculator(seqInstruction: Seq[Instruction],
                         NS: Int,
                         WE: Int,
                         wayNS: Int,
                         wayWE: Int): Int = {

    if (seqInstruction.nonEmpty) {
      val headInstruction: Instruction = seqInstruction.head

      headInstruction.direction match {
        case 'N' | 'S' =>
          val newPos: Int =
            if (headInstruction.direction == 'N') NS + headInstruction.argument
            else NS - headInstruction.argument

          RainRiskCalculator(seqInstruction.tail, newPos, WE, wayNS, wayWE)

        case 'E' | 'W' =>
          val newPos: Int =
            if (headInstruction.direction == 'E') WE + headInstruction.argument
            else WE - headInstruction.argument

          RainRiskCalculator(seqInstruction.tail, NS, newPos, wayNS, wayWE)

        case 'L' | 'R' =>
          val angle: Int =
            if (headInstruction.direction == 'L') headInstruction.argument
            else -headInstruction.argument

          val (newWayX: Int, newWayY: Int) =
            trigNewPosition(wayWE, wayNS, angle)

          RainRiskCalculator(seqInstruction.tail, NS, WE, newWayY, newWayX)

        case 'F' =>
          val newNS: Int = NS + wayNS * headInstruction.argument
          val newWE: Int = WE + wayWE * headInstruction.argument

          RainRiskCalculator(seqInstruction.tail, newNS, newWE, wayNS, wayWE)
      }
    } else Math.abs(NS) + Math.abs(WE)
  }

  //Part 2
  @scala.annotation.tailrec
  def RainRiskWayPointCalculator(seqInstr: Seq[Instruction],
                                 NS: Int,
                                 WE: Int,
                                 wayNS: Int,
                                 wayWE: Int): Int = {

    if (seqInstr.nonEmpty) {

      val headInstruction: Instruction = seqInstr.head

      headInstruction.direction match {
        case 'N' | 'S' =>
          val newNS: Int =
            if (headInstruction.direction == 'N')
              wayNS + headInstruction.argument
            else wayNS - headInstruction.argument

          RainRiskWayPointCalculator(seqInstr.tail, NS, WE, newNS, wayWE)

        case 'E' | 'W' =>
          val newWE: Int =
            if (headInstruction.direction == 'E')
              wayWE + headInstruction.argument
            else wayWE - headInstruction.argument

          RainRiskWayPointCalculator(seqInstr.tail, NS, WE, wayNS, newWE)

        case 'L' | 'R' =>
          val angle: Int =
            if (headInstruction.direction == 'L') headInstruction.argument
            else -headInstruction.argument

          val (newWayX: Int, newWayY: Int) =
            trigNewPosition(wayWE, wayNS, angle)

          RainRiskWayPointCalculator(seqInstr.tail, NS, WE, newWayY, newWayX)

        case 'F' =>
          val newNS: Int = NS + wayNS * headInstruction.argument
          val newWE: Int = WE + wayWE * headInstruction.argument

          RainRiskWayPointCalculator(seqInstr.tail, newNS, newWE, wayNS, wayWE)
      }
    } else Math.abs(NS) + Math.abs(WE)
  }
}
