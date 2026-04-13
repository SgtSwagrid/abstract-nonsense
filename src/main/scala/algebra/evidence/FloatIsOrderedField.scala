package io.github.sgtswagrid.nonsense
package algebra.evidence

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedField

trait FloatIsOrderedField:

  given OrderedField[Float] with

    override def negate(x: Float): Float             = -x
    override def add(x: Float, y: Float): Float      = x + y
    override def reciprocate(x: Float): Float        = 1.0F / x
    override def multiply(x: Float, y: Float): Float = x * y
    override def one: Float                          = 1.0F
    override def zero: Float                         = 0.0F
    override def compare(x: Float, y: Float): Int    = x.compareTo(y)
