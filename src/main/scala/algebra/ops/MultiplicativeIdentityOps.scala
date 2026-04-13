package io.github.sgtswagrid.nonsense
package algebra.ops

import io.github.sgtswagrid.nonsense.algebra.MultiplicativeIdentity

/** Extension methods for [[MultiplicativeIdentity]]. */
trait MultiplicativeIdentityOps:

  extension [X : MultiplicativeIdentity as X](x: X)

    /** @return `true` if this value equals [[one]]. */
    inline def isOne: Boolean = x == X.one

  /**
    * The unique representation of the multiplicative identity (`1`) in this
    * algebra system. Typically corresponds to values such as `1` or `1.0F`.
    */
  final inline def one[X : MultiplicativeIdentity as X]: X = X.one
