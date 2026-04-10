package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.MultiplicativeSemigroup
import io.github.sgtswagrid.nonsense.groups.ops.MultiplicativeSemigroupOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedMultiplicativeSemigroupBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedMultiplicativeSemigroup[X]
  extends MultiplicativeSemigroup[X], Ordering[X]

object OrderedMultiplicativeSemigroup
  extends OrderedMultiplicativeSemigroupBuilder,
          MultiplicativeSemigroupOps,
          OrderingOps:
  export io.github.sgtswagrid.nonsense.groups.ordered.OrderedMultiplicativeSemigroup
