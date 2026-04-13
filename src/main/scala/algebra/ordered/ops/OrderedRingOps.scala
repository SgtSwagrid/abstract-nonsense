package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.RingOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedRing

/** Extension methods for [[OrderedRing]]. */
trait OrderedRingOps
  extends RingOps, OrderedAdditiveGroupOps, OrderedDifferenceSemiringOps
