package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.DifferenceMonoid
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedDifferenceMonoidBuilder

/** Methods for constructing [[DifferenceMonoid]] type classes. */
trait DifferenceMonoidBuilder
  extends OrderedDifferenceMonoidBuilder,
          AdditiveGroupBuilder,
          DifferenceSemiringBuilder
