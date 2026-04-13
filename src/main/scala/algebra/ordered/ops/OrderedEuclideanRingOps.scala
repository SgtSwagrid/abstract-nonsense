package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.EuclideanRingOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedEuclideanRing

/** Extension methods for [[OrderedEuclideanRing]]. */
trait OrderedEuclideanRingOps
  extends EuclideanRingOps, OrderedRingOps, OrderedEuclideanMonoidOps
