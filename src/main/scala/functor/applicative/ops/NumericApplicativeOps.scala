package io.github.sgtswagrid.nonsense
package functor.applicative.ops

import io.github.sgtswagrid.nonsense.functor.covariant.NumericFunctor
import io.github.sgtswagrid.nonsense.functor.covariant.ops.MapOps

trait NumericApplicativeOps[
  Self[+X] <: MapOps[Self, Any, Numeric, X],
  Output : Numeric as Number,
] extends ZipOps[Self, Output]:

  final inline def add(that: Self[Output]): Self[Output] =
    zip(that).map(Number.plus)

  /** Alias for [[add]]. */
  final inline infix def + (that: Self[Output]): Self[Output] = add(that)

  final inline def sub(that: Self[Output]): Self[Output] =
    zip(that).map(Number.minus)

  /** Alias for [[sub]]. */
  final inline infix def - (that: Self[Output]): Self[Output] = sub(that)

  final inline infix def min(that: Self[Output]): Self[Output] =
    zip(that).map(Number.min)

  final inline infix def max(that: Self[Output]): Self[Output] =
    zip(that).map(Number.max)

  final inline def midpoint
    (using Number: Fractional[Output])
    (that: Self[Output])
    : Self[Output] = (this + that).map(Number.div(_, Number.fromInt(2)))
