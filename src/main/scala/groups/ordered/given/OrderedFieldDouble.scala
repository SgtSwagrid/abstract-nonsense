package io.github.sgtswagrid.nonsense
package groups.ordered.`given`

import io.github.sgtswagrid.nonsense.groups.ordered.OrderedField

trait OrderedFieldDouble:

  given OrderedField[Double] with

    override def negate(x: Double): Double              = -x
    override def add(x: Double, y: Double): Double      = x + y
    override def reciprocate(x: Double): Double         = 1.0 / x
    override def multiply(x: Double, y: Double): Double = x * y
    override def one: Double                            = 1.0
    override def zero: Double                           = 0.0
    override def compare(x: Double, y: Double): Int     = x.compareTo(y)
