package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.AdditiveGroupBuilder
import io.github.sgtswagrid.nonsense.groups.ops.AdditiveGroupOps
import scala.reflect.ClassTag

trait AdditiveGroup[X] extends AdditiveMonoid[X]:

  def negate(x: X): X
  def subtract(x: X, y: X): X = add(x, negate(y))

object AdditiveGroup extends AdditiveGroupBuilder, AdditiveGroupOps
