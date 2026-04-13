package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.SemiringOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedSemiring

/** Extension methods for [[OrderedSemiring]]. */
trait OrderedSemiringOps
  extends SemiringOps, OrderedAdditiveMonoidOps, OrderedMultiplicativeMonoidOps
