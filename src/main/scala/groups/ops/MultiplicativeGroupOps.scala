package io.github.sgtswagrid.nonsense
package groups.ops

import io.github.sgtswagrid.nonsense.groups.MultiplicativeGroup

/**
  * Extension methods for types forming a multiplicative group
  * ([[groups.MultiplicativeGroup]]).
  */
trait MultiplicativeGroupOps extends EuclideanMonoidOps:

  extension [X : MultiplicativeGroup as X](x: X)

    /** @return the multiplicative inverse of this value. */
    def reciprocal: X = X.reciprocate(x)
