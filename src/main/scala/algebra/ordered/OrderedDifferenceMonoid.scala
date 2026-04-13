package io.github.sgtswagrid.nonsense
package algebra.ordered

import io.github.sgtswagrid.nonsense.algebra.DifferenceMonoid
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedDifferenceMonoidBuilder
import io.github.sgtswagrid.nonsense.algebra.ordered.ops.OrderedDifferenceMonoidOps

/** An ordered version of [[DifferenceMonoid]]. */
trait OrderedDifferenceMonoid[X]
  extends DifferenceMonoid[X], OrderedAdditiveMonoid[X]

/**
  * The companion object for [[OrderedDifferenceMonoid]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedDifferenceMonoid.{*, given}
  * }}}
  * to receive all necessary syntax for working with ordered difference monoids.
  */
object OrderedDifferenceMonoid
  extends OrderedDifferenceMonoidBuilder, OrderedDifferenceMonoidOps:

  export io.github.sgtswagrid.nonsense.algebra.ordered.OrderedDifferenceMonoid

  /**
    * The [[OrderedDifferenceMonoid]] instance describing the current algebra
    * system.
    */
  inline def orderedDifferenceMonoid[
    X : OrderedDifferenceMonoid as orderedDifferenceMonoid,
  ]: OrderedDifferenceMonoid[X] = orderedDifferenceMonoid
