package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.AdditiveGroupOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedAdditiveGroup

/** Extension methods for [[OrderedAdditiveGroup]]. */
trait OrderedAdditiveGroupOps
  extends AdditiveGroupOps,
          OrderedDifferenceMonoidOps,
          OrderedAdditiveInverseOps
