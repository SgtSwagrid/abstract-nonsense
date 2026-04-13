package io.github.sgtswagrid.nonsense
package algebra

import io.github.sgtswagrid.nonsense.algebra.builder.DifferenceSemiringBuilder
import io.github.sgtswagrid.nonsense.algebra.ops.DifferenceSemiringOps

/** For algebraic structures with addition, subtraction, and multiplication. */
trait DifferenceSemiring[X] extends Semiring[X], DifferenceMonoid[X]

/**
  * The companion object for [[DifferenceSemiring]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.DifferenceSemiring.{*, given}
  * }}}
  * to receive all necessary syntax for working with difference semirings.
  */
object DifferenceSemiring
  extends DifferenceSemiringBuilder, DifferenceSemiringOps:

  export io.github.sgtswagrid.nonsense.algebra.DifferenceSemiring

  /** The [[DifferenceSemiring]] instance describing the current algebra system. */
  inline def differenceSemiring[X : DifferenceSemiring as differenceSemiring]
    : DifferenceSemiring[X] = differenceSemiring
