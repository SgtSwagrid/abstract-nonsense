package io.github.sgtswagrid.nonsense
package transform

import io.github.sgtswagrid.nonsense.groups.ordered.OrderedRing
import scala.reflect.ClassTag

trait ScaleOps[+Self[_], +X]:

  /** Uniformly scale this structure by a constant factor. */
  def scale[Y >: X : {OrderedRing, ClassTag}](factor: Y): Self[Y]

  /** Alias for [[scale]]. */
  final inline infix def * [Y >: X : {OrderedRing, ClassTag}]
    (factor: Y)
    : Self[Y] = scale(factor)
