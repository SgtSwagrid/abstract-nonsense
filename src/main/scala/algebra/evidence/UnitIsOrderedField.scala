package io.github.sgtswagrid.nonsense
package algebra.evidence

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedField

trait UnitIsOrderedField:

  given OrderedField[Unit] with

    override def negate(x: Unit): Unit            = ()
    override def add(x: Unit, y: Unit): Unit      = ()
    override def reciprocate(x: Unit): Unit       = ()
    override def multiply(x: Unit, y: Unit): Unit = ()
    override def one: Unit                        = ()
    override def zero: Unit                       = ()
    override def compare(x: Unit, y: Unit): Int   = 0
