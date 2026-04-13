package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.SemifieldOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedSemifield

/** Extension methods for [[OrderedSemifield]]. */
trait OrderedSemifieldOps
  extends SemifieldOps, OrderedSemiringOps, OrderedMultiplicativeGroupOps
