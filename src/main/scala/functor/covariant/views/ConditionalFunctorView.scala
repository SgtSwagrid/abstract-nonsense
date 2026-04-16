package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor
import io.github.sgtswagrid.nonsense.util.NoContext

/** A functor that only maps values that satisfy a given condition. */
class ConditionalFunctorView[
  +Self[+_ <: Codomain],
  -Codomain,
  +X <: Codomain,
]
  (
    base: BoundedFunctor[Self, Codomain, X],
    condition: X => Boolean,
  )
  extends BoundedFunctor[[Y <: Codomain] =>> Self[Y | X], Codomain, X]:

  override inline def map[Y <: Codomain : NoContext]
    (transform: X => Y)
    : Self[Y | X] = base.map:
    case value if condition(value) => transform(value)
    case value                     => value

  override def toString = base.toString
