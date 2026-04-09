package io.github.sgtswagrid.nonsense
package transform

trait NegateOps[+Self]:

  /** Produce the additive inverse of this structure. */
  def negate: Self

  /** Alias for [[negate]]. */
  final inline def unary_- : Self = negate
