package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.MultiplicativeInverse
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedMultiplicativeInverseBuilder

/** Methods for constructing [[MultiplicativeInverse]] type classes. */
trait MultiplicativeInverseBuilder
  extends OrderedMultiplicativeInverseBuilder, MultiplicativeGroupBuilder
