package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.AdditiveMonoid
import io.github.sgtswagrid.nonsense.groups.ops.AdditiveMonoidOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedAdditiveMonoidBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedAdditiveMonoid[X]
  extends AdditiveMonoid[X], OrderedAdditiveSemigroup[X], OrderedZero[X]

object OrderedAdditiveMonoid
  extends OrderedAdditiveMonoidBuilder, AdditiveMonoidOps, OrderingOps:
  export io.github.sgtswagrid.nonsense.groups.ordered.OrderedAdditiveMonoid
