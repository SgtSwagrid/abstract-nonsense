package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.projection

import io.github.sgtswagrid.nonsense.functor.bifunctor.RightBoundedBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.Functor
import io.github.sgtswagrid.nonsense.util.NoContext

/** A view of a [[Bifunctor]] that only maps values on the left. */
class BifunctorLeftView[+Self[+_, +_], +L, +R]
  (base: RightBoundedBifunctor[Self, ? >: R, L, R])
  extends BoundedBifunctorLeftView[Self, Any, L, R](base),
          Functor[[X] =>> Self[X, R], L]:

  override inline def map[l : NoContext](transform: L => l): Self[l, R] = base
    .bimap(transform)(identity)
