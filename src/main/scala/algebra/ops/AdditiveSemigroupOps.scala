package io.github.sgtswagrid.nonsense
package algebra.ops

import io.github.sgtswagrid.nonsense.algebra.AdditiveSemigroup

/** Extension methods for [[AdditiveSemigroup]]. */
trait AdditiveSemigroupOps:

  extension [X : AdditiveSemigroup as X](x: X)

    /** Computes the sum of both operands. */
    inline infix def + (y: X): X = X.add(x, y)

    /**
      * Multiplies this value on the left by a strictly positive integer.
      *
      * @throws IllegalArgumentException
      *   if `n ≤ 0`.
      */
    inline infix def *: (n: Int): X = X.scale(x, n)

    /**
      * Multiplies this value on the right by a strictly positive integer.
      *
      * @throws IllegalArgumentException
      *   if `n ≤ 0`.
      */
    inline infix def :* (n: Int): X = X.scale(x, n)
