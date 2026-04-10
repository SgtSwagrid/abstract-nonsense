package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.AdditiveGroup
import io.github.sgtswagrid.nonsense.groups.ops.AdditiveGroupOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedAdditiveGroupBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedAdditiveGroup[X] extends AdditiveGroup[X], OrderedAdditiveMonoid[X]

object OrderedAdditiveGroup
  extends OrderedAdditiveGroupBuilder, AdditiveGroupOps, OrderingOps
