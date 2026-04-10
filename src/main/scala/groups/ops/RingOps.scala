package io.github.sgtswagrid.nonsense
package groups.ops

import io.github.sgtswagrid.nonsense.groups.Ring

/** Extension methods for types forming a ring ([[Ring]]). */
trait RingOps extends AdditiveGroupOps, MultiplicativeMonoidOps:

  def two[X : Ring as X]: X         = X.two
  def negativeOne[X : Ring as X]: X = X.negativeOne
