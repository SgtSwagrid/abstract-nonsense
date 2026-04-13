package io.github.sgtswagrid.nonsense
package algebra.ordered

import io.github.sgtswagrid.nonsense.algebra.MultiplicativeMonoid
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedMultiplicativeMonoidBuilder
import io.github.sgtswagrid.nonsense.algebra.ordered.ops.OrderedMultiplicativeMonoidOps

/** An ordered version of [[MultiplicativeMonoid]]. */
trait OrderedMultiplicativeMonoid[X]
  extends MultiplicativeMonoid[X],
          OrderedMultiplicativeSemigroup[X],
          OrderedOne[X]

/**
  * The companion object for [[OrderedMultiplicativeMonoid]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedMultiplicativeMonoid.{*, given}
  * }}}
  * to receive all necessary syntax for working with ordered multiplicative
  * monoids.
  */
object OrderedMultiplicativeMonoid
  extends OrderedMultiplicativeMonoidBuilder, OrderedMultiplicativeMonoidOps:

  export io.github.sgtswagrid.nonsense.algebra.ordered.OrderedMultiplicativeMonoid

  /**
    * The [[OrderedMultiplicativeMonoid]] instance describing the current
    * algebra system.
    */
  inline def orderedMultiplicativeMonoid[
    X : OrderedMultiplicativeMonoid as orderedMultiplicativeMonoid,
  ]: OrderedMultiplicativeMonoid[X] = orderedMultiplicativeMonoid
