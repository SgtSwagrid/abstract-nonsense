package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.EuclideanMonoidBuilder
import io.github.sgtswagrid.nonsense.groups.ops.EuclideanMonoidOps

trait EuclideanMonoid[X] extends MultiplicativeMonoid[X]:

  def divide(x: X, y: X): X

object EuclideanMonoid extends EuclideanMonoidBuilder, EuclideanMonoidOps
