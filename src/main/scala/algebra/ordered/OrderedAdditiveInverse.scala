package io.github.sgtswagrid.nonsense
package algebra.ordered

import io.github.sgtswagrid.nonsense.algebra.AdditiveInverse
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedAdditiveInverseBuilder
import io.github.sgtswagrid.nonsense.algebra.ordered.ops.OrderedAdditiveInverseOps

/** An ordered version of [[AdditiveInverse]]. */
trait OrderedAdditiveInverse[X] extends AdditiveInverse[X], Ordering[X]

/**
  * The companion object for [[OrderedAdditiveInverse]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedAdditiveInverse.{*, given}
  * }}}
  * to receive all necessary syntax for working with ordered additive inverses.
  */
object OrderedAdditiveInverse
  extends OrderedAdditiveInverseBuilder, OrderedAdditiveInverseOps:

  export io.github.sgtswagrid.nonsense.algebra.ordered.OrderedAdditiveInverse

  /**
    * The [[OrderedAdditiveInverse]] instance describing the current algebra
    * system.
    */
  inline def orderedAdditiveInverse[
    X : OrderedAdditiveInverse as orderedAdditiveInverse,
  ]: OrderedAdditiveInverse[X] = orderedAdditiveInverse
