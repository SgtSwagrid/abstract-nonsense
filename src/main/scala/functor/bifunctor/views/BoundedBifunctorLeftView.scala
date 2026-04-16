package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.functor.bifunctor.BoundedBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor
import io.github.sgtswagrid.nonsense.functor.covariant.PartialFunctor.NoContext

/** A view of a [[BoundedBifunctor]] that only maps values on the left. */
class BoundedBifunctorLeftView[
  +Self[+_, +_],
  -LeftCodomain,
  +L <: LeftCodomain,
  +R,
]
  (base: BoundedBifunctor[Self, LeftCodomain, ? >: R, L, R])
  extends PartialBifunctorLeftView[Self, LeftCodomain, NoContext, L, R](base),
          BoundedFunctor[[X] =>> Self[X, R], LeftCodomain, L]:

  override protected def mapImpl[l <: LeftCodomain]
    (transform: L => l)
    : Self[l, R] = base.bimap(transform)(identity)
