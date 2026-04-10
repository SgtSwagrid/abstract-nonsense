package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.EuclideanMonoid
import io.github.sgtswagrid.nonsense.groups.ops.EuclideanMonoidOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedEuclideanMonoidBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedEuclideanMonoid[X]
  extends EuclideanMonoid[X], OrderedMultiplicativeMonoid[X]

object OrderedEuclideanMonoid
  extends OrderedEuclideanMonoidBuilder, EuclideanMonoidOps, OrderingOps:
  export io.github.sgtswagrid.nonsense.groups.ordered.OrderedEuclideanMonoid
