package io.github.sgtswagrid.nonsense
package misc

object NumericBoolean:

  given Numeric[Boolean] with

    override def plus(x: Boolean, y: Boolean): Boolean = x || y

    override def minus(x: Boolean, y: Boolean): Boolean = x && !y

    override def times(x: Boolean, y: Boolean): Boolean = x && y

    override def negate(x: Boolean): Boolean = !x

    override def fromInt(x: Int): Boolean = x != 0

    override def parseString(str: String): Option[Boolean] =
      str.toLowerCase match
        case "false" | "no" | "0" => Some(false)
        case "true" | "yes" | "1" => Some(true)
        case _                    => None

    override def toInt(x: Boolean): Int = if x then 1 else 0

    override def toLong(x: Boolean): Long = if x then 1L else 0L

    override def toFloat(x: Boolean): Float = if x then 1.0F else 0.0F

    override def toDouble(x: Boolean): Double = if x then 1.0 else 0.0

    override def compare(x: Boolean, y: Boolean): Int = toInt(x) - toInt(y)
