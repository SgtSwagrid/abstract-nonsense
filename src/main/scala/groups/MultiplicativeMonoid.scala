package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.MultiplicativeMonoidBuilder
import io.github.sgtswagrid.nonsense.groups.ops.MultiplicativeMonoidOps

trait MultiplicativeMonoid[X] extends MultiplicativeSemigroup[X], One[X]:

  def product(xs: Iterable[X]): X = productOption(xs).getOrElse(one)

object MultiplicativeMonoid extends MultiplicativeMonoidBuilder, MultiplicativeMonoidOps
