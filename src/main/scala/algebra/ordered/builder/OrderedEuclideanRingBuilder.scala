package io.github.sgtswagrid.nonsense
package algebra.ordered.builder

import io.github.sgtswagrid.nonsense.algebra.evidence.{
  IntIsOrderedEuclideanRing, LongIsOrderedEuclideanRing,
}
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedEuclideanRing

/** Methods for constructing [[OrderedEuclideanRing]] type classes. */
trait OrderedEuclideanRingBuilder
  extends OrderedFieldBuilder,
          IntIsOrderedEuclideanRing,
          LongIsOrderedEuclideanRing
