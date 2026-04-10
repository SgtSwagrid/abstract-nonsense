package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.MultiplicativeGroupBuilder
import io.github.sgtswagrid.nonsense.groups.ops.MultiplicativeGroupOps

trait MultiplicativeGroup[X] extends EuclideanMonoid[X]:

  def reciprocate(x: X): X
  override def divide(x: X, y: X): X = multiply(x, reciprocate(y))

object MultiplicativeGroup extends MultiplicativeGroupBuilder, MultiplicativeGroupOps
