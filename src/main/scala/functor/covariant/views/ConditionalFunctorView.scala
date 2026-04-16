package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/** A functor that only maps values that satisfy a given condition. */
final class ConditionalFunctorView[
  +Self[+_ <: Codomain],
  -Codomain,
  +X <: Codomain,
]
  (
    base: BoundedFunctor[Self, Codomain, X],
    condition: X => Boolean,
  )
  extends BoundedFunctor[[Y <: Codomain] =>> Self[Y | X], Codomain, X]:

  override protected inline def mapImpl[Y <: Codomain]
    (transform: X => Y)
    : Self[Y | X] = base.map:
    case value if condition(value) => transform(value)
    case value                     => value

  override def toString = base.toString
