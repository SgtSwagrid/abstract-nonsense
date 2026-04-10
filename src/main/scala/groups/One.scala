package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.OneBuilder
import io.github.sgtswagrid.nonsense.groups.ops.OneOps

trait One[+X]:

  def one: X

object One extends OneBuilder, OneOps
