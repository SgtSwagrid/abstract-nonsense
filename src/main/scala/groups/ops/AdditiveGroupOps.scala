package io.github.sgtswagrid.nonsense
package groups.ops

import io.github.sgtswagrid.nonsense.groups.AdditiveGroup

/**
  * Extension methods for types forming an additive group
  * ([[groups.AdditiveGroup]]).
  */
trait AdditiveGroupOps extends AdditiveMonoidOps:

  extension [X : AdditiveGroup as X](x: X)

    /** @return the additive inverse of this value. */
    def unary_- : X = X.negate(x)

    /** @return the difference of both operands. */
    infix def - (y: X): X = X.subtract(x, y)
