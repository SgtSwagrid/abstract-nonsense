package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.MultiplicativeSemigroup
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedMultiplicativeSemigroupBuilder

/** Methods for constructing [[MultiplicativeSemigroup]] type classes. */
trait MultiplicativeSemigroupBuilder
  extends OrderedMultiplicativeSemigroupBuilder, MultiplicativeMonoidBuilder
