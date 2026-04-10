package io.github.sgtswagrid.nonsense
package groups.ordered.ops

trait OrderingOps:

  extension [X : Ordering as X](x: X)

    def compare(y: X): Int = X.compare(x, y)

    inline infix def < (y: X): Boolean  = compare(y) < 0
    inline infix def <= (y: X): Boolean = compare(y) <= 0
    inline infix def > (y: X): Boolean  = compare(y) > 0
    inline infix def >= (y: X): Boolean = compare(y) >= 0
