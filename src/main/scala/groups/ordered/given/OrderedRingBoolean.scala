package io.github.sgtswagrid.nonsense
package groups.ordered.`given`

import io.github.sgtswagrid.nonsense.groups.ordered.OrderedRing

trait OrderedRingBoolean:

  given OrderedRing[Boolean] with

    override def negate(x: Boolean): Boolean               = !x
    override def add(x: Boolean, y: Boolean): Boolean      = x | y
    override def multiply(x: Boolean, y: Boolean): Boolean = x & y
    override def one: Boolean                              = true
    override def zero: Boolean                             = false
    override def compare(x: Boolean, y: Boolean): Int      = x.compareTo(y)
