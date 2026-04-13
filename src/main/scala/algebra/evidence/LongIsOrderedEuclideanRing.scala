package io.github.sgtswagrid.nonsense
package algebra.evidence

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedEuclideanRing

trait LongIsOrderedEuclideanRing:

  given OrderedEuclideanRing[Long] with

    override inline def negate(x: Long): Long            = -x
    override inline def add(x: Long, y: Long): Long      = x + y
    override inline def divide(x: Long, y: Long): Long   = x / y
    override inline def multiply(x: Long, y: Long): Long = x * y
    override def one: Long                               = 1L
    override def zero: Long                              = 0L
    override def compare(x: Long, y: Long): Int          = x.compareTo(y)
