package io.github.sgtswagrid.nonsense
package functor.covariant.ops

trait NumericFunctorOps[+Self[+_], +Content, -Codomain]
  extends MapOps[Self, Content, Codomain]:

  /** Replace each element with a version scaled by [[factor]]. */
  final inline def scale[Number >: Content <: Codomain : Numeric as Number]
    (factor: Number)
    : Self[Number] = map(Number.times(_, factor))

  /** Alias for [[scale]]. */
  final inline infix def * [Number >: Content <: Codomain : Numeric as Number]
    (factor: Number)
    : Self[Number] = scale(factor)

  /** Replace each element with a version divided by [[divisor]]. */
  final inline def divide[Number >: Content <: Codomain : Fractional as Number]
    (divisor: Number)
    : Self[Number] = map(Number.div(_, divisor))

  /** Alias for [[divide]]. */
  final inline infix def / [
    Number >: Content <: Codomain : Fractional as Number,
  ](divisor: Number): Self[Number] = divide(divisor)

  /** Replace each element with a version divided by [[divisor]] (rounded down). */
  final inline def floorDivide[
    Number >: Content <: Codomain : Integral as Number,
  ](divisor: Number): Self[Number] = map(Number.quot(_, divisor))

  /** Alias for [[floorDivide]]. */
  final inline infix def :/ [Number >: Content <: Codomain : Integral as Number]
    (divisor: Number)
    : Self[Number] = floorDivide(divisor)

  /** Replace each element with a version modulo [[base]]. */
  final inline def mod[Number >: Content <: Codomain : Integral as Number]
    (base: Number)
    : Self[Number] = map(Number.rem(_, base))

  /** Alias for [[mod]]. */
  final inline infix def % [Number >: Content <: Codomain : Integral as Number]
    (base: Number)
    : Self[Number] = mod(base)

  /** Replace each element with its additive inverse. */
  final inline def negate[Number >: Content <: Codomain : Numeric as Number]
    : Self[Number] = map(Number.negate)

  /** Alias for [[negate]]. */
  final inline def unary_-[Number >: Content <: Codomain : Numeric as Number]
    : Self[Number] = negate

  /** Replace each element with its absolute value. */
  final inline def abs[Number >: Content <: Codomain : Numeric as Number]
    : Self[Number] = map(Number.abs)

  /** Replace each element with its sign (`1` or `-1`). */
  final inline def sign[Number >: Content <: Codomain : Numeric as Number]
    : Self[Number] = map(Number.sign)

  /** Replace each element with its multiplicative inverse. */
  final inline def reciprocal[
    Number >: Content <: Codomain : Fractional as Number,
  ]: Self[Number] = map(Number.div(Number.one, _))

  /**
    * Replace each non-zero element with `true` and each zero element with
    * `false`.
    */
  final inline def support[Number >: Content : Numeric as Number]
    (using Boolean <:< Codomain)
    : Self[Boolean] = mapWithEvidence(_ != Number.zero)

  /** Replace each element with its logical negation. */
  final inline def invert
    (
      using Content <:< Boolean,
      Boolean <:< Codomain,
    )
    : Self[Boolean] = mapWithEvidence(value => !value)
