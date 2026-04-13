package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.AdditiveMonoidOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedAdditiveMonoid

/** Extension methods for [[OrderedAdditiveMonoid]]. */
trait OrderedAdditiveMonoidOps
  extends AdditiveMonoidOps,
          OrderedAdditiveSemigroupOps,
          OrderedAdditiveIdentityOps
