package io.github.sgtswagrid.nonsense
package algebra

import io.github.sgtswagrid.nonsense.algebra.builder.MultiplicativeMonoidBuilder
import io.github.sgtswagrid.nonsense.algebra.ops.MultiplicativeMonoidOps

/** For algebraic structures with multiplication and an identity. */
trait MultiplicativeMonoid[X]
  extends MultiplicativeSemigroup[X], MultiplicativeIdentity[X]:

  /**
    * Computes the product of all values in [[xs]], i.e. `xs₁ × xs₂ × …`, or
    * else [[one]] if [[xs]] is empty.
    */
  final inline def product(xs: Iterable[X]): X =
    productOption(xs).getOrElse(one)

  /**
    * Computes [[x]] raised to the power [[n]], for any non-negative [[n]].
    *
    * @throws IllegalArgumentException
    *   if `n < 0`.
    */
  def pow(x: X, n: Int): X =

    require(
      n >= 0,
      s"Negative exponents ($n) aren't supported in this algebra.",
    )

    if n == 0 then one else super.pow(x, n)

/**
  * The companion object for [[MultiplicativeMonoid]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.MultiplicativeMonoid.{*, given}
  * }}}
  * to receive all necessary syntax for working with multiplicative monoids.
  */
object MultiplicativeMonoid
  extends MultiplicativeMonoidBuilder, MultiplicativeMonoidOps:

  export io.github.sgtswagrid.nonsense.algebra.MultiplicativeMonoid

  /**
    * The [[MultiplicativeMonoid]] instance describing the current algebra
    * system.
    */
  inline def multiplicativeMonoid[
    X : MultiplicativeMonoid as multiplicativeMonoid,
  ]: MultiplicativeMonoid[X] = multiplicativeMonoid
