package io.github.sgtswagrid.nonsense
package algebra.ordered.builder

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedDifferenceMonoid

/** Methods for constructing [[OrderedDifferenceMonoid]] type classes. */
trait OrderedDifferenceMonoidBuilder
  extends OrderedAdditiveGroupBuilder, OrderedDifferenceSemiringBuilder
