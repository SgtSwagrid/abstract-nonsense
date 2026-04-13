package io.github.sgtswagrid.nonsense
package algebra.ops

import io.github.sgtswagrid.nonsense.algebra.Ring

/** Extension methods for [[Ring]]. */
trait RingOps extends AdditiveGroupOps, DifferenceSemiringOps:

  extension [X : {Ring as R, Ordering as O}](x: X)

    /** Computes the sign of this value as `-1`, `0`, or `1`. */
    inline def signum: X =
      if O.lt(x, R.zero) then R.negativeOne
      else if O.gt(x, R.zero) then R.one
      else R.zero

  /** The unique representation of `-1` in this algebra system. */
  def negativeOne[X : Ring as X]: X = X.negativeOne
