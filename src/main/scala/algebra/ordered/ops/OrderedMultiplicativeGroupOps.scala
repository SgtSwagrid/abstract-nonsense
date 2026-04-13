package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.MultiplicativeGroupOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedMultiplicativeGroup

/** Extension methods for [[OrderedMultiplicativeGroup]]. */
trait OrderedMultiplicativeGroupOps
  extends MultiplicativeGroupOps,
          OrderedEuclideanMonoidOps,
          OrderedMultiplicativeInverseOps
