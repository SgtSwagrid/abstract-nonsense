package io.github.sgtswagrid.nonsense
package misc

object NumericNothing:

  given Numeric[Nothing] with

    override def plus(x: Nothing, y: Nothing): Nothing =
      throw new IllegalStateException

    override def minus(x: Nothing, y: Nothing): Nothing =
      throw new IllegalStateException

    override def times(x: Nothing, y: Nothing): Nothing =
      throw new IllegalStateException

    override def negate(x: Nothing): Nothing = throw new IllegalStateException

    override def fromInt(x: Int): Nothing = throw new IllegalStateException

    override def parseString(str: String): Option[Nothing] = None

    override def toInt(x: Nothing): Int = throw new IllegalStateException

    override def toLong(x: Nothing): Long = throw new IllegalStateException

    override def toFloat(x: Nothing): Float = throw new IllegalStateException

    override def toDouble(x: Nothing): Double = throw new IllegalStateException

    override def compare(x: Nothing, y: Nothing): Int =
      throw new IllegalStateException
