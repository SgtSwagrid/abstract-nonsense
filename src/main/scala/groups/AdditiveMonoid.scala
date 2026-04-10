package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.AdditiveMonoidBuilder
import io.github.sgtswagrid.nonsense.groups.ops.AdditiveMonoidOps

trait AdditiveMonoid[X] extends AdditiveSemigroup[X], Zero[X]:

  def sum(xs: Iterable[X]): X = sumOption(xs).getOrElse(zero)

object AdditiveMonoid extends AdditiveMonoidBuilder, AdditiveMonoidOps
