package io.github.sgtswagrid.nonsense
package algebra.ordered

import io.github.sgtswagrid.nonsense.algebra.EuclideanMonoid
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedEuclideanMonoidBuilder
import io.github.sgtswagrid.nonsense.algebra.ordered.ops.OrderedEuclideanMonoidOps

/** An ordered version of [[EuclideanMonoid]]. */
trait OrderedEuclideanMonoid[X]
  extends EuclideanMonoid[X], OrderedMultiplicativeMonoid[X]

/**
  * The companion object for [[OrderedEuclideanMonoid]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedEuclideanMonoid.{*, given}
  * }}}
  * to receive all necessary syntax for working with ordered Euclidean monoids.
  */
object OrderedEuclideanMonoid
  extends OrderedEuclideanMonoidBuilder, OrderedEuclideanMonoidOps:

  export io.github.sgtswagrid.nonsense.algebra.ordered.OrderedEuclideanMonoid

  /**
    * The [[OrderedEuclideanMonoid]] instance describing the current algebra
    * system.
    */
  inline def orderedEuclideanMonoid[
    X : OrderedEuclideanMonoid as orderedEuclideanMonoid,
  ]: OrderedEuclideanMonoid[X] = orderedEuclideanMonoid
