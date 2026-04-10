package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.Ring
import io.github.sgtswagrid.nonsense.groups.ops.RingOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedRingBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedRing[X]
  extends Ring[X], OrderedAdditiveGroup[X], OrderedMultiplicativeMonoid[X]

object OrderedRing extends OrderedRingBuilder, RingOps, OrderingOps
