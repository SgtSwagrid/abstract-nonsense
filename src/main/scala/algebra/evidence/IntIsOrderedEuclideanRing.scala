package io.github.sgtswagrid.nonsense
package algebra.evidence

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedEuclideanRing

trait IntIsOrderedEuclideanRing:

  given OrderedEuclideanRing[Int] with

    override inline def negate(x: Int): Int           = -x
    override inline def add(x: Int, y: Int): Int      = x + y
    override inline def divide(x: Int, y: Int): Int   = x / y
    override inline def multiply(x: Int, y: Int): Int = x * y
    override def one: Int                             = 1
    override def zero: Int                            = 0
    override def compare(x: Int, y: Int): Int         = x.compareTo(y)
