package io.github.sgtswagrid.nonsense
package groups.ops

import io.github.sgtswagrid.nonsense.groups.One

/** Extension methods for types with a multiplicative identity ([[One]]). */
trait OneOps:

  extension [X : One as X](x: X)

    /** @return `true` if this value equals [[One.one]]. */
    def isOne: Boolean = x == X.one

  /**
    * The multiplicative identity in the current algebra system. It is assured
    * that multiplying by `one` (i.e. using [[*]]) is a no-op. Typically
    * corresponds to values such as `1` or `1.0F`.
    */
  def one[X : One as X]: X = X.one
