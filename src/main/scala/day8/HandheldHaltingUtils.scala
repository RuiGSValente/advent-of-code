package day8

object HandheldHaltingUtils {

  case class Instruction(operation: String, argument: Int)

  def splitTextToInstructions(seqStr: Seq[String]): Array[Instruction] = {
    seqStr.flatMap { str =>
      val splitStr: Array[String] = str.split(" ")
      for {
        op <- splitStr.headOption.map(_.toString)
        arg <- splitStr.lift(1).map(_.toInt)
      } yield Instruction(op, arg)
    }
  }.toArray

  //Part 1
  def accumulatorCalculator(instructions: Seq[Instruction],
                            accPositions: Seq[Int],
                            accValue: Int): (Seq[Int], Int) = {

    def condition(newPositionIncrement: Int,
                  newAccIncrement: Int): (Seq[Int], Int) = {

      val newPosition: Int = accPositions.last + newPositionIncrement

      if (accPositions.contains(newPosition) | newPosition + 1 > instructions.length)
        (accPositions, accValue + newAccIncrement)
      else
        accumulatorCalculator(
          instructions,
          accPositions :+ newPosition,
          accValue + newAccIncrement
        )
    }

    val newValue: Instruction = instructions(accPositions.last)

    newValue.operation match {
      case "nop" => condition(1, 0)
      case "acc" => condition(1, newValue.argument)
      case "jmp" => condition(newValue.argument, 0)
    }
  }

  //Part 2
  @scala.annotation.tailrec
  def accumulatorCalculator2(instructions: Seq[Instruction],
                             indexSeq: Seq[Int]): Int = {

    val lastInstr: Instruction = instructions(indexSeq.last)

    lastInstr.operation match {
      case "nop" | "jmp" =>
        val newInstr: Seq[Instruction] = instructions.updated(
          indexSeq.last,
          Instruction(
            operation = if (lastInstr.operation == "nop") "jmp" else "nop",
            argument = lastInstr.argument
          )
        )

        val (newCalcIndex, newCalcAcc): (Seq[Int], Int) =
          accumulatorCalculator(newInstr, Seq(0), 0)

        if (newCalcIndex.last + 1 == instructions.length)
          newCalcAcc
        else
          accumulatorCalculator2(instructions, indexSeq.dropRight(1))

      case _ => accumulatorCalculator2(instructions, indexSeq.dropRight(1))
    }
  }
}
