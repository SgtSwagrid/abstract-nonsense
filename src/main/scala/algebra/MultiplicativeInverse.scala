package io.github.sgtswagrid.nonsense
package algebra

import io.github.sgtswagrid.nonsense.algebra.builder.MultiplicativeInverseBuilder
import io.github.sgtswagrid.nonsense.algebra.ops.MultiplicativeInverseOps

/** For algebraic structures with a multiplicative inverse. */
trait MultiplicativeInverse[X]:

  /**
    * Computes the multiplicative inverse (reciprocal) of a value [[x]], i.e.
    * `1 / x`.
    *
    * @note
    *   All implementations must be involutions, i.e.
    *   `reciprocate(reciprocate(x)) == x`.
    */
  def reciprocate(x: X): X

/**
  * The companion object for [[MultiplicativeInverse]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.MultiplicativeInverse.{*, given}
  * }}}
  * to receive all necessary syntax for working with multiplicative inverses.
  */
object MultiplicativeInverse
  extends MultiplicativeInverseBuilder, MultiplicativeInverseOps:

  export io.github.sgtswagrid.nonsense.algebra.MultiplicativeInverse

  /**
    * The [[MultiplicativeInverse]] instance describing the current algebra
    * system.
    */
  inline def multiplicativeInverse[
    X : MultiplicativeInverse as multiplicativeInverse,
  ]: MultiplicativeInverse[X] = multiplicativeInverse
