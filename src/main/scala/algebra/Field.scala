package io.github.sgtswagrid.nonsense
package algebra

import io.github.sgtswagrid.nonsense.algebra.builder.FieldBuilder
import io.github.sgtswagrid.nonsense.algebra.ops.FieldOps

/** For algebraic structures with addition, multiplication, and inverses. */
trait Field[X] extends EuclideanRing[X], Semifield[X]:

  override inline def mod(x: X, y: X): X = zero

/**
  * The companion object for [[Field]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.Field.{*, given}
  * }}}
  * to receive all necessary syntax for working with fields.
  */
object Field extends FieldBuilder, FieldOps:

  export io.github.sgtswagrid.nonsense.algebra.Field

  /** The [[Field]] instance describing the current algebra system. */
  inline def field[X : Field as field]: Field[X] = field
