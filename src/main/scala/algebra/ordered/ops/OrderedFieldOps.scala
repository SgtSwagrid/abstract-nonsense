package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.FieldOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedField

/** Extension methods for [[OrderedField]]. */
trait OrderedFieldOps
  extends FieldOps, OrderedEuclideanRingOps, OrderedMultiplicativeGroupOps
