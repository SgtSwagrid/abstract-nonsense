package io.github.sgtswagrid.nonsense
package algebra.evidence

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedRing

trait BooleanIsOrderedRing:

  given OrderedRing[Boolean] with

    override def negate(x: Boolean): Boolean               = !x
    override def add(x: Boolean, y: Boolean): Boolean      = x ^ y
    override def multiply(x: Boolean, y: Boolean): Boolean = x & y
    override def one: Boolean                              = true
    override def zero: Boolean                             = false
    override def compare(x: Boolean, y: Boolean): Int      = x.compareTo(y)
