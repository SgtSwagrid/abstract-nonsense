package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.Ring
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedRingBuilder

/** Methods for constructing [[Ring]] type classes. */
trait RingBuilder extends OrderedRingBuilder, EuclideanRingBuilder
