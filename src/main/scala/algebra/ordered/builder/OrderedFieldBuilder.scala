package io.github.sgtswagrid.nonsense
package algebra.ordered.builder

import io.github.sgtswagrid.nonsense.algebra.evidence.{
  DoubleIsOrderedEuclideanRing, FloatIsOrderedField, NothingIsOrderedField,
}
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedField

/** Methods for constructing [[OrderedField]] type classes. */
trait OrderedFieldBuilder
  extends DoubleIsOrderedEuclideanRing,
          FloatIsOrderedField,
          NothingIsOrderedField
