package io.github.sgtswagrid.nonsense
package algebra.ordered.builder

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedMultiplicativeMonoid

/** Methods for constructing [[OrderedMultiplicativeMonoid]] type classes. */
trait OrderedMultiplicativeMonoidBuilder
  extends OrderedEuclideanMonoidBuilder, OrderedSemiringBuilder
