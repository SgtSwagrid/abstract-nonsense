package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.AdditiveSemigroup
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedAdditiveSemigroupBuilder

/** Methods for constructing [[AdditiveSemigroup]] type classes. */
trait AdditiveSemigroupBuilder
  extends OrderedAdditiveSemigroupBuilder, AdditiveMonoidBuilder
