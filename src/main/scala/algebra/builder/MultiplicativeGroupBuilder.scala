package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.MultiplicativeGroup
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedMultiplicativeGroupBuilder

/** Methods for constructing [[MultiplicativeGroup]] type classes. */
trait MultiplicativeGroupBuilder
  extends OrderedMultiplicativeGroupBuilder, SemifieldBuilder
