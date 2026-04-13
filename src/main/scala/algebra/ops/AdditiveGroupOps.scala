package io.github.sgtswagrid.nonsense
package algebra.ops

import io.github.sgtswagrid.nonsense.algebra.AdditiveGroup

/** Extension methods for [[AdditiveGroup]]. */
trait AdditiveGroupOps extends DifferenceMonoidOps, AdditiveInverseOps:

  extension [X : AdditiveGroup as X](x: X)

    /** Multiplies this value on the left by any integer. */
    inline infix def *: (n: Int): X = X.scale(x, n)

    /** Multiplies this value on the right by any integer. */
    inline infix def :* (n: Int): X = X.scale(x, n)

  extension [X : {AdditiveGroup as A, Ordering as O}](x: X)

    /** Computes the absolute value of this value. */
    inline def abs: X = if O.lt(x, A.zero) then A.negate(x) else x
