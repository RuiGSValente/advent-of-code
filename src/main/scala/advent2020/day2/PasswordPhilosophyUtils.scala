package advent2020.day2

object PasswordPhilosophyUtils {

  case class Password(firstValue: Int,
                      secondValue: Int,
                      letter: Char,
                      passwordString: String)

  //Example: "4-10 m: mmmmqmcmlmvmm"
  def splitString(listString: Seq[String]): Seq[Password] = {
    listString.map { string =>
      val splitBySpace: Array[String] = string.split(" ")

      val minMaxSplitByDash: Array[Int] =
        splitBySpace(0).split("-").map(_.toInt)

      Password(
        firstValue = minMaxSplitByDash(0),
        secondValue = minMaxSplitByDash(1),
        letter = splitBySpace(1).charAt(0),
        passwordString = splitBySpace(2)
      )
    }
  }

  def countValidPasswordPart1(seqPassword: Seq[String]): Int = {
    splitString(seqPassword)
      .count { password =>
        val numberOfChar: Int =
          password.passwordString.count(_ == password.letter)

        numberOfChar <= password.secondValue && numberOfChar >= password.firstValue
      }
  }

  def countValidPasswordPart2(seqPassword: Seq[String]): Int = {
    splitString(seqPassword)
      .count { password =>
        val firstPositionContainsChar: Boolean =
          password.passwordString(password.firstValue - 1) == password.letter

        val secondPositionContainsChar: Boolean =
          password.passwordString(password.secondValue - 1) == password.letter

        Seq(firstPositionContainsChar, secondPositionContainsChar).count(
          _ == true
        ) == 1
      }
  }

}
