package io.github.sgtswagrid.nonsense
package algebra.ops

import io.github.sgtswagrid.nonsense.algebra.DifferenceMonoid

/** Extension methods for [[DifferenceMonoid]]. */
trait DifferenceMonoidOps extends AdditiveMonoidOps:

  extension [X : DifferenceMonoid as X](x: X)

    /** Computes the difference between both operands. */
    inline infix def - (y: X): X = X.subtract(x, y)
