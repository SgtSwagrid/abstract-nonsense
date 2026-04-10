package io.github.sgtswagrid.nonsense
package groups.ops

import io.github.sgtswagrid.nonsense.groups.AdditiveSemigroup

/**
  * Extension methods for types with an addition operation
  * ([[AdditiveSemigroup]]).
  */
trait AdditiveSemigroupOps:

  extension [X : AdditiveSemigroup as X](x: X)

    /** @return the sum of both operands. */
    infix def + (y: X): X = X.add(x, y)
