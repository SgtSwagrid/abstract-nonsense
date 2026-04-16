package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.projection

import io.github.sgtswagrid.nonsense.functor.bifunctor.BoundedBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor
import io.github.sgtswagrid.nonsense.util.NoContext

/** A view of a [[BoundedBifunctor]] that only maps values on the left. */
class BoundedBifunctorLeftView[
  +Self[+_, +_],
  -LeftCodomain,
  +L <: LeftCodomain,
  +R,
]
  (base: BoundedBifunctor[Self, LeftCodomain, ? >: R, L, R])
  extends PartialBifunctorLeftView[
    Self,
    LeftCodomain,
    [l, r] =>> DummyImplicit,
    L,
    R,
  ](base),
          BoundedFunctor[[X] =>> Self[X, R], LeftCodomain, L]:

  override def map[l <: LeftCodomain : NoContext]
    (transform: L => l)
    : Self[l, R] = base.bimap(transform)(identity)
