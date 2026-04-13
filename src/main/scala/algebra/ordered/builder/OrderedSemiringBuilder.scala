package io.github.sgtswagrid.nonsense
package algebra.ordered.builder

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedSemiring

/** Methods for constructing [[OrderedSemiring]] type classes. */
trait OrderedSemiringBuilder
  extends OrderedDifferenceSemiringBuilder, OrderedSemifieldBuilder
