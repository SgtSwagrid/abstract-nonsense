package io.github.sgtswagrid.nonsense
package algebra.ordered.builder

import io.github.sgtswagrid.nonsense.algebra.evidence.BooleanIsOrderedRing
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedRing

/** Methods for constructing [[OrderedRing]] type classes. */
trait OrderedRingBuilder
  extends OrderedEuclideanRingBuilder, BooleanIsOrderedRing
