package io.github.sgtswagrid.nonsense
package algebra

import io.github.sgtswagrid.nonsense.algebra.builder.AdditiveInverseBuilder
import io.github.sgtswagrid.nonsense.algebra.ops.AdditiveInverseOps

/** For algebraic structures with an additive inverse. */
trait AdditiveInverse[X]:

  /**
    * Computes the additive inverse (negative) of a value [[x]], i.e. `-x`.
    *
    * @note
    *   All implementations must be involutions, i.e. `negate(negate(x)) == x`.
    */
  def negate(x: X): X

/**
  * The companion object for [[AdditiveInverse]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.AdditiveInverse.{*, given}
  * }}}
  * to receive all necessary syntax for working with additive inverses.
  */
object AdditiveInverse extends AdditiveInverseBuilder, AdditiveInverseOps:

  export io.github.sgtswagrid.nonsense.algebra.AdditiveInverse

  /** The [[AdditiveInverse]] instance describing the current algebra system. */
  inline def additiveInverse[X : AdditiveInverse as additiveInverse]
    : AdditiveInverse[X] = additiveInverse
