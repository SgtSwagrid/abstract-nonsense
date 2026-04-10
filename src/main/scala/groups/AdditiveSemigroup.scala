package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.AdditiveSemigroupBuilder
import io.github.sgtswagrid.nonsense.groups.ops.AdditiveSemigroupOps

trait AdditiveSemigroup[X]:

  def add(x: X, y: X): X

  def sumOption(xs: Iterable[X]): Option[X] = xs.reduceOption(add)
  def sum(x: X, xs: X*): X                  = sumOption(x +: xs).get

object AdditiveSemigroup extends AdditiveSemigroupBuilder, AdditiveSemigroupOps:
  export io.github.sgtswagrid.nonsense.groups.AdditiveSemigroup
