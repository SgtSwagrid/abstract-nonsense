package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.ZeroBuilder
import io.github.sgtswagrid.nonsense.groups.ops.ZeroOps

trait Zero[+X]:

  def zero: X

object Zero extends ZeroBuilder, ZeroOps:
  export io.github.sgtswagrid.nonsense.groups.Zero
