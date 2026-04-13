package io.github.sgtswagrid.nonsense
package algebra

import io.github.sgtswagrid.nonsense.algebra.builder.EuclideanRingBuilder
import io.github.sgtswagrid.nonsense.algebra.ops.EuclideanRingOps
import scala.annotation.tailrec

/** For algebraic structures with addition, multiplication, and division. */
trait EuclideanRing[X] extends Ring[X], EuclideanMonoid[X]:

  /** Computes the signed remainder between two values [[x]] and [[y]]. */
  inline def mod(x: X, y: X): X = subtract(x, multiply(divide(x, y), y))

  /** Computes the greatest common divisor of two values [[x]] and [[y]]. */
  @tailrec
  final def gcd(x: X, y: X): X = if y == zero then x else gcd(y, mod(x, y))

  /** Computes the least common multiple of two values [[x]] and [[y]]. */
  final inline def lcm(x: X, y: X): X =
    val g = gcd(x, y)
    if g == zero then g else multiply(divide(x, g), y)

/**
  * The companion object for [[EuclideanRing]]. Import as
  * {{{
  * import io.github.sgtswagrid.nonsense.algebra.EuclideanRing.{*, given}
  * }}}
  * to receive all necessary syntax for working with Euclidean rings.
  */
object EuclideanRing extends EuclideanRingBuilder, EuclideanRingOps:

  export io.github.sgtswagrid.nonsense.algebra.EuclideanRing

  /** The [[EuclideanRing]] instance describing the current algebra system. */
  inline def euclideanRing[X : EuclideanRing as euclideanRing]
    : EuclideanRing[X] = euclideanRing
