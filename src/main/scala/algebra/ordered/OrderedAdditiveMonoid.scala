package io.github.sgtswagrid.nonsense
package algebra.ordered

import io.github.sgtswagrid.nonsense.algebra.AdditiveMonoid
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedAdditiveMonoidBuilder
import io.github.sgtswagrid.nonsense.algebra.ordered.ops.OrderedAdditiveMonoidOps

/** An ordered version of [[AdditiveMonoid]]. */
trait OrderedAdditiveMonoid[X]
  extends AdditiveMonoid[X], OrderedAdditiveSemigroup[X], OrderedZero[X]

/**
  * The companion object for [[OrderedAdditiveMonoid]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedAdditiveMonoid.{*, given}
  * }}}
  * to receive all necessary syntax for working with ordered additive monoids.
  */
object OrderedAdditiveMonoid
  extends OrderedAdditiveMonoidBuilder, OrderedAdditiveMonoidOps:

  export io.github.sgtswagrid.nonsense.algebra.ordered.OrderedAdditiveMonoid

  /**
    * The [[OrderedAdditiveMonoid]] instance describing the current algebra
    * system.
    */
  inline def orderedAdditiveMonoid[
    X : OrderedAdditiveMonoid as orderedAdditiveMonoid,
  ]: OrderedAdditiveMonoid[X] = orderedAdditiveMonoid
