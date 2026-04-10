package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.MultiplicativeSemigroupBuilder
import io.github.sgtswagrid.nonsense.groups.ops.MultiplicativeSemigroupOps

trait MultiplicativeSemigroup[X]:

  def multiply(x: X, y: X): X

  def productOption(xs: Iterable[X]): Option[X] = xs.reduceOption(multiply)
  def product(x: X, xs: X*): X                  = productOption(x +: xs).get

object MultiplicativeSemigroup extends MultiplicativeSemigroupBuilder, MultiplicativeSemigroupOps:
  export io.github.sgtswagrid.nonsense.groups.MultiplicativeSemigroup
