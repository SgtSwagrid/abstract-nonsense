package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

import io.github.sgtswagrid.nonsense.algebra.ops.MultiplicativeMonoidOps
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedMultiplicativeMonoid

/** Extension methods for [[OrderedMultiplicativeMonoid]]. */
trait OrderedMultiplicativeMonoidOps
  extends MultiplicativeMonoidOps,
          OrderedMultiplicativeSemigroupOps,
          OrderedMultiplicativeIdentityOps
