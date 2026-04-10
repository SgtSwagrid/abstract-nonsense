package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.EuclideanRingBuilder
import io.github.sgtswagrid.nonsense.groups.ops.EuclideanRingOps

trait EuclideanRing[X] extends Ring[X], EuclideanMonoid[X]

object EuclideanRing extends EuclideanRingBuilder, EuclideanRingOps:
  export io.github.sgtswagrid.nonsense.groups.EuclideanRing
