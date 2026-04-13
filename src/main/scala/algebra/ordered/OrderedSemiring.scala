package io.github.sgtswagrid.nonsense
package algebra.ordered

import io.github.sgtswagrid.nonsense.algebra.Semiring
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedSemiringBuilder
import io.github.sgtswagrid.nonsense.algebra.ordered.ops.OrderedSemiringOps

/** An ordered version of [[Semiring]]. */
trait OrderedSemiring[X]
  extends Semiring[X], OrderedAdditiveMonoid[X], OrderedMultiplicativeMonoid[X]

/**
  * The companion object for [[OrderedSemiring]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedSemiring.{*, given}
  * }}}
  * to receive all necessary syntax for working with ordered semirings.
  */
object OrderedSemiring extends OrderedSemiringBuilder, OrderedSemiringOps:

  export io.github.sgtswagrid.nonsense.algebra.ordered.OrderedSemiring

  /** The [[OrderedSemiring]] instance describing the current algebra system. */
  inline def orderedSemiring[X : OrderedSemiring as orderedSemiring]
    : OrderedSemiring[X] = orderedSemiring
