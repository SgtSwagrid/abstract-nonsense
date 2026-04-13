package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.EuclideanRing
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedEuclideanRingBuilder

/** Methods for constructing [[EuclideanRing]] type classes. */
trait EuclideanRingBuilder extends OrderedEuclideanRingBuilder, FieldBuilder
