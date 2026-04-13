package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.MultiplicativeMonoid
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedMultiplicativeMonoidBuilder

/** Methods for constructing [[MultiplicativeMonoid]] type classes. */
trait MultiplicativeMonoidBuilder
  extends OrderedMultiplicativeMonoidBuilder,
          EuclideanMonoidBuilder,
          SemiringBuilder
