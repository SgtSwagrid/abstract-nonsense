package io.github.sgtswagrid.nonsense
package groups.ordered

import io.github.sgtswagrid.nonsense.groups.Field
import io.github.sgtswagrid.nonsense.groups.ops.FieldOps
import io.github.sgtswagrid.nonsense.groups.ordered.builder.OrderedFieldBuilder
import io.github.sgtswagrid.nonsense.groups.ordered.ops.OrderingOps

trait OrderedField[X]
  extends Field[X], OrderedEuclideanRing[X], OrderedMultiplicativeGroup[X]

object OrderedField extends OrderedFieldBuilder, FieldOps, OrderingOps:
  export io.github.sgtswagrid.nonsense.groups.ordered.OrderedField
