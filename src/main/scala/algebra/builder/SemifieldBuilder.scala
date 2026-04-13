package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.Semifield
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedSemifieldBuilder

/** Methods for constructing [[Semifield]] type classes. */
trait SemifieldBuilder extends OrderedSemifieldBuilder, FieldBuilder
