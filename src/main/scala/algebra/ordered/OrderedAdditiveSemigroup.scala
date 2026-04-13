package io.github.sgtswagrid.nonsense
package algebra.ordered

import io.github.sgtswagrid.nonsense.algebra.AdditiveSemigroup
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedAdditiveSemigroupBuilder
import io.github.sgtswagrid.nonsense.algebra.ordered.ops.OrderedAdditiveSemigroupOps

/** An ordered version of [[AdditiveSemigroup]]. */
trait OrderedAdditiveSemigroup[X] extends AdditiveSemigroup[X], Ordering[X]

/**
  * The companion object for [[OrderedAdditiveSemigroup]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedAdditiveSemigroup.{*, given}
  * }}}
  * to receive all necessary syntax for working with ordered additive
  * semigroups.
  */
object OrderedAdditiveSemigroup
  extends OrderedAdditiveSemigroupBuilder, OrderedAdditiveSemigroupOps:

  export io.github.sgtswagrid.nonsense.algebra.ordered.OrderedAdditiveSemigroup

  /**
    * The [[OrderedAdditiveSemigroup]] instance describing the current algebra
    * system.
    */
  inline def orderedAdditiveSemigroup[
    X : OrderedAdditiveSemigroup as orderedAdditiveSemigroup,
  ]: OrderedAdditiveSemigroup[X] = orderedAdditiveSemigroup
