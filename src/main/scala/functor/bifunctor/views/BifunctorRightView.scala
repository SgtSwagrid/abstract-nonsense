package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.util.NoContext
import io.github.sgtswagrid.nonsense.functor.bifunctor.LeftBoundedBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.Functor

/** A view of a [[Bifunctor]] that only maps values on the right. */
class BifunctorRightView[+Self[+_, +_], +L, +R]
  (base: LeftBoundedBifunctor[Self, ? >: L, L, R])
  extends BoundedBifunctorRightView[Self, Any, L, R](base),
          Functor[[X] =>> Self[L, X], R]:

  override inline def map[r : NoContext](transform: R => r): Self[L, r] = base
    .bimap(identity)(transform)
