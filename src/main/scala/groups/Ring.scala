package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.RingBuilder
import io.github.sgtswagrid.nonsense.groups.ops.RingOps

trait Ring[X] extends AdditiveGroup[X], MultiplicativeMonoid[X]:

  def two: X         = add(one, one)
  def negativeOne: X = negate(one)

object Ring extends RingBuilder, RingOps:
  export io.github.sgtswagrid.nonsense.groups.Ring
