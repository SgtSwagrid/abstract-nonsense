package io.github.sgtswagrid.nonsense
package algebra.ordered

import io.github.sgtswagrid.nonsense.algebra.AdditiveIdentity
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedAdditiveIdentityBuilder
import io.github.sgtswagrid.nonsense.algebra.ordered.ops.OrderedAdditiveIdentityOps

/** An ordered version of [[AdditiveIdentity]]. */
trait OrderedZero[X] extends AdditiveIdentity[X], Ordering[X]:

  /** @return `true` if a value [[x]] is strictly positive, i.e. `x > 0`. */
  final inline def isPositive(x: X): Boolean = gt(x, zero)

  /** @return `true` if a value [[x]] is strictly negative, i.e. `x < 0`. */
  final inline def isNegative(x: X): Boolean = lt(x, zero)

  /** @return `true` if a value [[x]] is positive or [[zero]], i.e. `x ≥ 0`. */
  final inline def isNonNegative(x: X): Boolean = gteq(x, zero)

  /** @return `true` if a value [[x]] is negative or [[zero]], i.e. `x ≤ 0`. */
  final inline def isNonPositive(x: X): Boolean = lteq(x, zero)

/**
  * The companion object for [[OrderedZero]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedZero.{*, given}
  * }}}
  * to receive all necessary syntax for working with ordered zero.
  */
object OrderedZero
  extends OrderedAdditiveIdentityBuilder, OrderedAdditiveIdentityOps:

  export io.github.sgtswagrid.nonsense.algebra.ordered.OrderedZero

  /** The [[OrderedZero]] instance describing the current algebra system. */
  inline def orderedZero[X : OrderedZero as orderedZero]: OrderedZero[X] =
    orderedZero
