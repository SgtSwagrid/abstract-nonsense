package io.github.sgtswagrid.nonsense
package algebra.ops

import io.github.sgtswagrid.nonsense.algebra.MultiplicativeInverse

/** Extension methods for [[MultiplicativeInverse]]. */
trait MultiplicativeInverseOps:

  extension [X : MultiplicativeInverse as X](x: X)

    /** Computes the multiplicative inverse (reciprocal) of this value. */
    inline def reciprocal: X = X.reciprocate(x)
