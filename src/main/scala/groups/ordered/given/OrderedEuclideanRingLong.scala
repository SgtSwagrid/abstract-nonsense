package io.github.sgtswagrid.nonsense
package groups.ordered.`given`

import io.github.sgtswagrid.nonsense.groups.ordered.OrderedEuclideanRing

trait OrderedEuclideanRingLong:

  given OrderedEuclideanRing[Long] with

    override inline def negate(x: Long): Long            = -x
    override inline def add(x: Long, y: Long): Long      = x + y
    override inline def divide(x: Long, y: Long): Long   = x / y
    override inline def multiply(x: Long, y: Long): Long = x * y
    override def one: Long                               = 1L
    override def zero: Long                              = 0L
    override def compare(x: Long, y: Long): Int          = x.compareTo(y)
