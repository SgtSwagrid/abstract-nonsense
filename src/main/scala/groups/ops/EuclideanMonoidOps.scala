package io.github.sgtswagrid.nonsense
package groups.ops

import io.github.sgtswagrid.nonsense.groups.EuclideanMonoid

/**
  * Extension methods for types forming a Euclidean monoid
  * ([[EuclideanMonoid]]).
  */
trait EuclideanMonoidOps extends MultiplicativeMonoidOps:

  extension [X : EuclideanMonoid as X](x: X)

    /** @return the quotient of both operands. */
    infix def / (y: X): X = X.divide(x, y)
