package utils

import java.lang.Math.{cos, sin, toRadians}

object MathUtils {

  //Calculate new positions of 2 points (x, y) after a rotation from an angle
  def trigNewPosition(oldX: Int, oldY: Int, angle: Int): (Int, Int) = {
    val newPointX: Int =
      (oldX * cos(toRadians(angle)) - oldY * sin(toRadians(angle))).round.toInt

    val newPointY: Int =
      (oldY * cos(toRadians(angle)) + oldX * sin(toRadians(angle))).round.toInt

    (newPointX, newPointY)
  }

}
