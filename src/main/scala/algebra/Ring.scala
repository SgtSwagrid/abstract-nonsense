package io.github.sgtswagrid.nonsense
package algebra

import io.github.sgtswagrid.nonsense.algebra.builder.RingBuilder
import io.github.sgtswagrid.nonsense.algebra.ops.RingOps

/** For algebraic structures with addition, negation, and multiplication. */
trait Ring[X] extends AdditiveGroup[X], DifferenceSemiring[X]:

  /** The unique representation of `-1` in this algebra system. */
  def negativeOne: X = negate(one)

/**
  * The companion object for [[Ring]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.Ring.{*, given}
  * }}}
  * to receive all necessary syntax for working with rings.
  */
object Ring extends RingBuilder, RingOps:

  export io.github.sgtswagrid.nonsense.algebra.Ring

  /** The [[Ring]] instance describing the current algebra system. */
  inline def ring[X : Ring as ring]: Ring[X] = ring
