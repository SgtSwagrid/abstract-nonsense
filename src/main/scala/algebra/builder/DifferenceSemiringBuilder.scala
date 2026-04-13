package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.DifferenceSemiring
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedDifferenceSemiringBuilder

/** Methods for constructing [[DifferenceSemiring]] type classes. */
trait DifferenceSemiringBuilder
  extends OrderedDifferenceSemiringBuilder, RingBuilder
