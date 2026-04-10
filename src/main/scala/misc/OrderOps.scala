package io.github.sgtswagrid.nonsense
package misc

import scala.math.Ordering

object OrderOps:

  /**
    * A version of [[scala.math.Ordered]] where the type consists of an
    * underlying ordered type [[X]] and a wrapper [[Self]].
    */
  trait Wrapped[Self[+_], +X] extends SelfOps[Self[X]]:

    /**
      * Determine the relative order of two elements in `Self[Y]`, where the
      * first element is `this` instance.
      *
      * @return
      *   `-1` if in-order (`this < x`), `1` if out-of-order (`this > x`), and
      *   `0` if equal (`this == x`).
      */
    def compare[Y >: X : Ordering](x: Self[Y]): Int

    /** @return `true` if and only if `this` is smaller than [[x]]. */
    infix def < [Y >: X : Ordering](x: Self[Y]): Boolean = compare(x) < 0

    /** @return `true` if and only if `this` is not larger than [[x]]. */
    infix def <= [Y >: X : Ordering](x: Self[Y]): Boolean = compare(x) <= 0

    /** @return `true` if and only if `this` is larger than [[x]]. */
    infix def > [Y >: X : Ordering](x: Self[Y]): Boolean = compare(x) > 0

    /** @return `true` if and only if `this` is not smaller than [[x]]. */
    infix def >= [Y >: X : Ordering](x: Self[Y]): Boolean = compare(x) >= 0

    /** @return The smaller of `this` or `that`. Defaults to `this` in ties. */
    infix def min[Y >: X : Ordering](that: Self[Y]): Self[Y] =
      if this > that then that else self

    /** @return The larger of `this` or `that`. Defaults to `this` in ties. */
    infix def max[Y >: X : Ordering](that: Self[Y]): Self[Y] =
      if this < that then that else self

  /**
    * A [covariant](https://docs.scala-lang.org/tour/variances.html#covariance)
    * version of [[scala.math.Ordered]].
    */
  trait Covariant[+X] extends Wrapped[[Y] =>> Y, X]

  /**
    * A
    * [contravariant](https://docs.scala-lang.org/tour/variances.html#contravariance)
    * version of [[scala.math.Ordered]].
    */
  trait Contravariant[-X]:

    /**
      * Determine the relative order of two elements in `Self[Y]`, where the
      * first element is `this` instance.
      *
      * @return
      *   `-1` if in-order (`this < x`), `1` if out-of-order (`this > x`), and
      *   `0` if equal (`this == x`).
      */
    def compare(x: X): Int

    /** @return `true` if and only if `this` is smaller than [[x]]. */
    inline infix def < (x: X): Boolean = compare(x) < 0

    /** @return `true` if and only if `this` is not larger than [[x]]. */
    inline infix def <= (x: X): Boolean = compare(x) <= 0

    /** @return `true` if and only if `this` is larger than [[x]]. */
    inline infix def > (x: X): Boolean = compare(x) > 0

    /** @return `true` if and only if `this` is not smaller than [[x]]. */
    inline infix def >= (x: X): Boolean = compare(x) >= 0

  /**
    * A version of [[scala.math.Ordered]] that also provides the operators
    * [[min]] and [[max]].
    */
  trait Invariant[X <: OrderOps.Invariant[X]]
    extends Contravariant[X], SelfOps[X]:

    /** @return The smaller of `this` or `that`. Defaults to `this` in ties. */
    inline infix def min(that: X): X = if this > that then that else self

    /** @return The larger of `this` or `that`. Defaults to `this` in ties. */
    inline infix def max(that: X): X = if this < that then that else self

  given [Self <: OrderOps.Contravariant[Self]]
    (using order: OrderOps.Contravariant[Self])
    : Ordering[Self] with
    def compare(x: Self, y: Self): Int = x.compare(y)
