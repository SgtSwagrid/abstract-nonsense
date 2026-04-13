package io.github.sgtswagrid.nonsense
package algebra.ordered.builder

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedEuclideanMonoid

/** Methods for constructing [[OrderedEuclideanMonoid]] type classes. */
trait OrderedEuclideanMonoidBuilder
  extends OrderedMultiplicativeGroupBuilder, OrderedEuclideanRingBuilder
