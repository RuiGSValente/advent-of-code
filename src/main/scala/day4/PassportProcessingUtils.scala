package day4

object PassportProcessingUtils {

  def splitFields(seq: Seq[String]): Int = {
    seq.count { str =>
      Seq("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid").forall(str.contains)
    }
  }

  /*
  Part2:
   */
  def getField(str: String, fieldName: String): Option[String] = {
    str
      .split(" ")
      .flatMap { str =>
        if (str.contains(fieldName)) str.split(":").lastOption
        else None
      }
      .toSeq
      .headOption
  }

  /*
    byr (Birth Year) - four digits; at least 1920 and at most 2002.
   */
  def byrCondition(str: String): Boolean =
    getField(str, "byr").exists(str => str.toInt >= 1920 && str.toInt <= 2002)

  /*
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
   */
  def iyrCondition(str: String): Boolean =
    getField(str, "iyr").exists(str => str.toInt >= 2010 && str.toInt <= 2020)

  /*
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
   */
  def eyrCondition(str: String): Boolean =
    getField(str, "eyr").exists(str => str.toInt >= 2020 && str.toInt <= 2030)

  /*
  hgt (Height) - a number followed by either cm or in:
    - If cm, the number must be at least 150 and at most 193.
    - If in, the number must be at least 59 and at most 76.
   */
  def hgtCondition(str: String): Boolean =
    getField(str, "hgt").exists { str =>
      val splitCharNumber = str.partition(_.isLetter)
      (splitCharNumber._1 == "cm" && splitCharNumber._2.toInt >= 150 && splitCharNumber._2.toInt <= 193) ||
      (splitCharNumber._1 == "in" && splitCharNumber._2.toInt >= 59 && splitCharNumber._2.toInt <= 76)
    }

  /*
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
   */
  def hclCondition(str: String): Boolean =
    getField(str, "hcl").exists(
      str => str.startsWith("#") && str.drop(1).matches("^[a-f0-9]{6}$")
    )

  /*
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
   */
  def eclCondition(str: String): Boolean =
    getField(str, "ecl").exists(
      str => Seq("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(str)
    )

  /*
    pid (Passport ID) - a nine-digit number, including leading zeroes.
   */
  def pidCondition(str: String): Boolean =
    getField(str, "pid").exists(_.matches("^[0-9]{9}$"))

  def checkPassportCondition(seqStr: Seq[String]): Int = {
    seqStr.count { str =>
      byrCondition(str) &&
      iyrCondition(str) &&
      eyrCondition(str) &&
      hgtCondition(str) &&
      hclCondition(str) &&
      eclCondition(str) &&
      pidCondition(str)
    }
  }
}
