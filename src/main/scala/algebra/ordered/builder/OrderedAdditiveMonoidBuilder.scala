package io.github.sgtswagrid.nonsense
package algebra.ordered.builder

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedAdditiveMonoid

/** Methods for constructing [[OrderedAdditiveMonoid]] type classes. */
trait OrderedAdditiveMonoidBuilder
  extends OrderedDifferenceMonoidBuilder, OrderedSemiringBuilder
