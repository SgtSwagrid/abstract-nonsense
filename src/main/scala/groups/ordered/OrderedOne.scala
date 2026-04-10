package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.One
import io.github.sgtswagrid.nonsense.groups.ops.OneOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedOneBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedOne[X] extends One[X], Ordering[X]

object OrderedOne extends OrderedOneBuilder, OneOps, OrderingOps:
  export io.github.sgtswagrid.nonsense.groups.ordered.OrderedOne
