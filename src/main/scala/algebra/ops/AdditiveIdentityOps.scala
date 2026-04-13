package io.github.sgtswagrid.nonsense
package algebra.ops

import io.github.sgtswagrid.nonsense.algebra.AdditiveIdentity

/** Extension methods for [[AdditiveIdentity]]. */
trait AdditiveIdentityOps:

  extension [X : AdditiveIdentity as X](x: X)

    /** @return `true` if this value equals [[zero]]. */
    inline def isZero: Boolean = x == X.zero

  /**
    * The unique representation of the additive identity (`0`) in this algebra
    * system. Typically corresponds to values such as `0`, `0.0F`, or
    * `Seq.empty`.
    */
  final inline def zero[X : AdditiveIdentity as X]: X = X.zero
