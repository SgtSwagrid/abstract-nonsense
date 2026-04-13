package io.github.sgtswagrid.nonsense
package algebra

import io.github.sgtswagrid.nonsense.algebra.builder.SemifieldBuilder
import io.github.sgtswagrid.nonsense.algebra.ops.SemifieldOps

/** For algebraic structures with addition, multiplication, and reciprocation. */
trait Semifield[X] extends Semiring[X], MultiplicativeGroup[X]

/**
  * The companion object for [[Semifield]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.Semifield.{*, given}
  * }}}
  * to receive all necessary syntax for working with semifields.
  */
object Semifield extends SemifieldBuilder, SemifieldOps:

  export io.github.sgtswagrid.nonsense.algebra.Semifield

  /** The [[Semifield]] instance describing the current algebra system. */
  inline def semifield[X : Semifield as semifield]: Semifield[X] = semifield
