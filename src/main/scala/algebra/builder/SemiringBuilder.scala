package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.Semiring
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedSemiringBuilder

/** Methods for constructing [[Semiring]] type classes. */
trait SemiringBuilder
  extends OrderedSemiringBuilder, DifferenceSemiringBuilder, SemifieldBuilder
