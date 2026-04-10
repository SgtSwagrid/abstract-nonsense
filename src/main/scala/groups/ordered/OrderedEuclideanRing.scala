package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.EuclideanRing
import io.github.sgtswagrid.nonsense.groups.ops.EuclideanRingOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedEuclideanRingBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedEuclideanRing[X]
  extends EuclideanRing[X], OrderedRing[X], OrderedEuclideanMonoid[X]

object OrderedEuclideanRing
  extends OrderedEuclideanRingBuilder, EuclideanRingOps, OrderingOps
