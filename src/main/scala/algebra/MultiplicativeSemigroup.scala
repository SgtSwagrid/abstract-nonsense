package io.github.sgtswagrid.nonsense
package algebra

import io.github.sgtswagrid.nonsense.algebra.builder.MultiplicativeSemigroupBuilder
import io.github.sgtswagrid.nonsense.algebra.ops.MultiplicativeSemigroupOps
import scala.annotation.tailrec

/** For algebraic structures with an associative multiplication operator. */
trait MultiplicativeSemigroup[X]:

  /**
    * Computes the product of two values [[x]] and [[y]], i.e. `x × y`.
    *
    * @note
    *   All implementations must be associative, i.e.
    *   `(x × y) × z == x × (y × z)`.
    */
  def multiply(x: X, y: X): X

  /**
    * Computes the product of all values in [[xs]], i.e. `xs₁ × xs₂ × …`, or
    * else [[None]] if [[xs]] is empty.
    */
  final inline def productOption(xs: Iterable[X]): Option[X] =
    xs.reduceOption(multiply)

  /**
    * Computes the product of [[x]] and all values in [[xs]]s, i.e.
    * `x × xs₁ × xs₂ × …`.
    */
  final inline def product(x: X, xs: X*): X = productOption(x +: xs).get

  /**
    * Computes [[x]] raised to the power [[n]], for any strictly positive [[n]].
    *
    * @throws IllegalArgumentException
    *   if `n ≤ 0`.
    */
  inline def pow(x: X, n: Int): X =

    require(
      n > 0,
      s"Non-positive exponents ($n) aren't supported in this algebra.",
    )

    @tailrec
    def recurse(x: X, n: Int): X =
      if n == 1 then x
      else if n % 2 == 0 then recurse(multiply(x, x), n / 2)
      else multiply(x, recurse(x, n - 1))

    recurse(x, n)

/**
  * The companion object for [[MultiplicativeSemigroup]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.MultiplicativeSemigroup.{*, given}
  * }}}
  * to receive all necessary syntax for working with multiplicative semigroups.
  */
object MultiplicativeSemigroup
  extends MultiplicativeSemigroupBuilder, MultiplicativeSemigroupOps:

  export io.github.sgtswagrid.nonsense.algebra.MultiplicativeSemigroup

  /**
    * The [[MultiplicativeSemigroup]] instance describing the current algebra
    * system.
    */
  inline def multiplicativeSemigroup[
    X : MultiplicativeSemigroup as multiplicativeSemigroup,
  ]: MultiplicativeSemigroup[X] = multiplicativeSemigroup
