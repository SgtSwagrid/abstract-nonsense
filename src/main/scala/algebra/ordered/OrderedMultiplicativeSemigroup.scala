package io.github.sgtswagrid.nonsense
package algebra.ordered

import io.github.sgtswagrid.nonsense.algebra.MultiplicativeSemigroup
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedMultiplicativeSemigroupBuilder
import io.github.sgtswagrid.nonsense.algebra.ordered.ops.OrderedMultiplicativeSemigroupOps

/** An ordered version of [[MultiplicativeSemigroup]]. */
trait OrderedMultiplicativeSemigroup[X]
  extends MultiplicativeSemigroup[X], Ordering[X]

/**
  * The companion object for [[OrderedMultiplicativeSemigroup]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedMultiplicativeSemigroup.{*, given}
  * }}}
  * to receive all necessary syntax for working with ordered multiplicative
  * semigroups.
  */
object OrderedMultiplicativeSemigroup
  extends OrderedMultiplicativeSemigroupBuilder,
          OrderedMultiplicativeSemigroupOps:

  export io.github.sgtswagrid.nonsense.algebra.ordered.OrderedMultiplicativeSemigroup

  /**
    * The [[OrderedMultiplicativeSemigroup]] instance describing the current
    * algebra system.
    */
  inline def orderedMultiplicativeSemigroup[
    X : OrderedMultiplicativeSemigroup as orderedMultiplicativeSemigroup,
  ]: OrderedMultiplicativeSemigroup[X] = orderedMultiplicativeSemigroup
