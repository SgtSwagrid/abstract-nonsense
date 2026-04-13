package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.AdditiveMonoid
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedAdditiveMonoidBuilder

/** Methods for constructing [[AdditiveMonoid]] type classes. */
trait AdditiveMonoidBuilder
  extends OrderedAdditiveMonoidBuilder, DifferenceMonoidBuilder, SemiringBuilder
