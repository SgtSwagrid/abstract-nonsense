package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.MultiplicativeMonoid
import io.github.sgtswagrid.nonsense.groups.ops.MultiplicativeMonoidOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedMultiplicativeMonoidBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedMultiplicativeMonoid[X]
  extends MultiplicativeMonoid[X],
          OrderedMultiplicativeSemigroup[X],
          OrderedOne[X]

object OrderedMultiplicativeMonoid
  extends OrderedMultiplicativeMonoidBuilder,
          MultiplicativeMonoidOps,
          OrderingOps:
  export io.github.sgtswagrid.nonsense.groups.ordered.OrderedMultiplicativeMonoid
