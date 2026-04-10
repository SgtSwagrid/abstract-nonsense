package io.github.sgtswagrid.nonsense
package groups.ops

import io.github.sgtswagrid.nonsense.groups.Zero

/** Extension methods for types with an additive identity ([[Zero]]). */
trait ZeroOps:

  extension [X : Zero as X](x: X)

    /** @return `true` if this value equals [[Zero.zero]]. */
    def isZero: Boolean = x == X.zero

  /**
    * The additive identity in the current algebra system. It is assured that
    * adding `zero` (i.e. using [[+]]) is a no-op. Typically corresponds to
    * values such as `0`, `0.0F`, or `Seq.empty`.
    */
  def zero[X : Zero as X]: X = X.zero
