package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.EuclideanMonoidOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedEuclideanMonoid

/** Extension methods for [[OrderedEuclideanMonoid]]. */
trait OrderedEuclideanMonoidOps
  extends EuclideanMonoidOps, OrderedMultiplicativeMonoidOps
