package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.DifferenceSemiringOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedDifferenceSemiring

/** Extension methods for [[OrderedDifferenceSemiring]]. */
trait OrderedDifferenceSemiringOps
  extends DifferenceSemiringOps, OrderedSemiringOps, OrderedDifferenceMonoidOps
