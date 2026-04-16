package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.util.NoContext
import io.github.sgtswagrid.nonsense.functor.bifunctor.BoundedBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/** A view of a [[BoundedBifunctor]] that only maps values on the right. */
class BoundedBifunctorRightView[
  +Self[+_, +_],
  -RightCodomain,
  +L,
  +R <: RightCodomain,
]
  (base: BoundedBifunctor[Self, ? >: L, RightCodomain, L, R])
  extends PartialBifunctorRightView[Self, RightCodomain, NoContext, L, R](base),
          BoundedFunctor[[X] =>> Self[L, X], RightCodomain, R]:

  override def map[r <: RightCodomain : NoContext]
    (transform: R => r)
    : Self[L, r] = base.bimap(identity)(transform)
