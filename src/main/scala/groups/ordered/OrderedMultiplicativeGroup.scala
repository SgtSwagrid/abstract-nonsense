package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.MultiplicativeGroup
import io.github.sgtswagrid.nonsense.groups.ops.MultiplicativeGroupOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedMultiplicativeGroupBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedMultiplicativeGroup[X]
  extends MultiplicativeGroup[X], OrderedEuclideanMonoid[X]

object OrderedMultiplicativeGroup
  extends OrderedMultiplicativeGroupBuilder, MultiplicativeGroupOps, OrderingOps:
  export io.github.sgtswagrid.nonsense.groups.ordered.OrderedMultiplicativeGroup
