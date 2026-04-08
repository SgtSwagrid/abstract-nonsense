package io.github.sgtswagrid.nonsense
package functor.applicative.ops

import io.github.sgtswagrid.nonsense.functor.applicative.Applicative

trait NumericApplicativeOps[Self[+X] <: Applicative[Self, X], +Output]
  extends ZipOps[Self, Output]:

  final inline def add[Number >: Output : Numeric as Number]
    (that: Self[Number])
    : Self[Number] = zipWith(that)(Number.plus)

  /** Alias for [[add]]. */
  final inline infix def + [Number >: Output : Numeric as Number]
    (that: Self[Number])
    : Self[Number] = add(that)

  final inline def sub[Number >: Output : Numeric as Number]
    (that: Self[Number])
    : Self[Number] = zipWith(that)(Number.minus)

  /** Alias for [[sub]]. */
  final inline infix def - [Number >: Output : Numeric as Number]
    (that: Self[Number])
    : Self[Number] = sub(that)

  final inline infix def min[Number >: Output : Numeric as Number]
    (that: Self[Number])
    : Self[Number] = zipWith(that)(Number.min)

  final inline infix def max[Number >: Output : Numeric as Number]
    (that: Self[Number])
    : Self[Number] = zipWith(that)(Number.max)

  final inline def midpoint[Number >: Output : Fractional as Number]
    (that: Self[Number])
    : Self[Number] = (this + that) / Number.fromInt(2)
