package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.AdditiveSemigroup
import io.github.sgtswagrid.nonsense.groups.ops.AdditiveSemigroupOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedAdditiveSemigroupBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedAdditiveSemigroup[X] extends AdditiveSemigroup[X], Ordering[X]

object OrderedAdditiveSemigroup
  extends OrderedAdditiveSemigroupBuilder, AdditiveSemigroupOps, OrderingOps:
  export io.github.sgtswagrid.nonsense.groups.ordered.OrderedAdditiveSemigroup
