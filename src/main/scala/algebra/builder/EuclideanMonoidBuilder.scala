package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.EuclideanMonoid
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedEuclideanMonoidBuilder

/** Methods for constructing [[EuclideanMonoid]] type classes. */
trait EuclideanMonoidBuilder
  extends OrderedEuclideanMonoidBuilder,
          MultiplicativeGroupBuilder,
          EuclideanRingBuilder
