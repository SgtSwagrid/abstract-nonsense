package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.Zero
import io.github.sgtswagrid.nonsense.groups.ops.ZeroOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedZeroBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedZero[X] extends Zero[X], Ordering[X]

object OrderedZero extends OrderedZeroBuilder, ZeroOps, OrderingOps:
  export io.github.sgtswagrid.nonsense.groups.ordered.OrderedZero
