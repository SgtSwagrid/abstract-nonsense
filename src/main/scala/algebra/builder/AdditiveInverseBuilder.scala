package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.AdditiveInverse
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedAdditiveInverseBuilder

/** Methods for constructing [[AdditiveInverse]] type classes. */
trait AdditiveInverseBuilder
  extends OrderedAdditiveInverseBuilder, AdditiveGroupBuilder
