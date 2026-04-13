package io.github.sgtswagrid.nonsense
package algebra.ordered.ops

/** Extension methods for ordered structures. */
trait OrderingOps:

  extension [X : Ordering as X](x: X)

    /**
      * Determines the relative order of both operands
      *
      * @return
      *   `-1` if in-order (`this < x`), `1` if out-of-order (`this > x`), and
      *   `0` if equal (`this == x`).
      */
    def compare(y: X): Int = X.compare(x, y)

    /** @return `true` if the left operand is smaller than the right. */
    inline infix def < (y: X): Boolean = compare(y) < 0

    /** @return `true` if the left operand is not larger than the right. */
    inline infix def <= (y: X): Boolean = compare(y) <= 0

    /** @return `true` if the right operand is larger than the left. */
    inline infix def > (y: X): Boolean = compare(y) > 0

    /** @return `true` if the right operand is not larger than the left. */
    inline infix def >= (y: X): Boolean = compare(y) >= 0

    /** @return the smaller of both operands. */
    inline infix def min(y: X): X = if x <= y then x else y

    /** @return the larger of both operands. */
    inline infix def max(y: X): X = if x >= y then x else y
