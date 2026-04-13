package io.github.sgtswagrid.nonsense
package algebra.ordered

import io.github.sgtswagrid.nonsense.algebra.MultiplicativeInverse
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedMultiplicativeInverseBuilder
import io.github.sgtswagrid.nonsense.algebra.ordered.ops.OrderedMultiplicativeInverseOps

/** An ordered version of [[MultiplicativeInverse]]. */
trait OrderedMultiplicativeInverse[X]
  extends MultiplicativeInverse[X], Ordering[X]

/**
  * The companion object for [[OrderedMultiplicativeInverse]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedMultiplicativeInverse.{*, given}
  * }}}
  * to receive all necessary syntax for working with ordered multiplicative
  * inverses.
  */
object OrderedMultiplicativeInverse
  extends OrderedMultiplicativeInverseBuilder, OrderedMultiplicativeInverseOps:

  export io.github.sgtswagrid.nonsense.algebra.ordered.OrderedMultiplicativeInverse

  /**
    * The [[OrderedMultiplicativeInverse]] instance describing the current
    * algebra system.
    */
  inline def orderedMultiplicativeInverse[
    X : OrderedMultiplicativeInverse as orderedMultiplicativeInverse,
  ]: OrderedMultiplicativeInverse[X] = orderedMultiplicativeInverse
