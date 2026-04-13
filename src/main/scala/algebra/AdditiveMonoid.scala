package io.github.sgtswagrid.nonsense
package algebra

import io.github.sgtswagrid.nonsense.algebra.builder.AdditiveMonoidBuilder
import io.github.sgtswagrid.nonsense.algebra.ops.AdditiveMonoidOps

/** For algebraic structures with addition and an identity. */
trait AdditiveMonoid[X] extends AdditiveSemigroup[X], AdditiveIdentity[X]:

  /**
    * Computes the sum of all values in [[xs]], i.e. `xs₁ + xs₂ + …`, or else
    * [[zero]] if [[xs]] is empty.
    */
  final inline def sum(xs: Iterable[X]): X = sumOption(xs).getOrElse(zero)

  /**
    * Computes [[x]] multiplied by [[n]], for any non-negative [[n]].
    *
    * @throws IllegalArgumentException
    *   if `n < 0`.
    */
  override def scale(x: X, n: Int): X =

    require(
      n >= 0,
      s"Negative multipliers ($n) aren't supported in this algebra.",
    )

    if n == 0 then zero else super.scale(x, n)

/**
  * The companion object for [[AdditiveMonoid]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.AdditiveMonoid.{*, given}
  * }}}
  * to receive all necessary syntax for working with additive monoids.
  */
object AdditiveMonoid extends AdditiveMonoidBuilder, AdditiveMonoidOps:

  export io.github.sgtswagrid.nonsense.algebra.AdditiveMonoid

  /** The [[AdditiveMonoid]] instance describing the current algebra system. */
  inline def additiveMonoid[X : AdditiveMonoid as additiveMonoid]
    : AdditiveMonoid[X] = additiveMonoid
