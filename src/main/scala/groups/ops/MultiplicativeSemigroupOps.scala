package io.github.sgtswagrid.nonsense
package groups.ops

import io.github.sgtswagrid.nonsense.groups.MultiplicativeSemigroup

/**
  * Extension methods for types with a multiplication operation
  * ([[MultiplicativeSemigroup]]).
  */
trait MultiplicativeSemigroupOps:

  extension [X : MultiplicativeSemigroup as X](x: X)

    /** @return the product of both operands. */
    infix def * (y: X): X = X.multiply(x, y)
