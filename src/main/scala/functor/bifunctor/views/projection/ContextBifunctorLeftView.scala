package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.projection

import io.github.sgtswagrid.nonsense.functor.bifunctor.ContextBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.ContextFunctor
import scala.annotation.unchecked.uncheckedVariance

/** A view of a [[ContextBifunctor]] that only maps values on the left. */
class ContextBifunctorLeftView[+Self[+_, +_], -Context[_], +L, +R]
  (base: ContextBifunctor[Self, Context, L, R])
  extends PartialBifunctorLeftView[Self, Any, [l, r] =>> Context[l | r], L, R](
    base,
  ),
          ContextFunctor[
            [X] =>> Self[X, R],
            [X] =>> Context[X | (R @uncheckedVariance)],
            L,
          ]:

  override def map[l : [X] =>> Context[X | (R @uncheckedVariance)]]
    (transform: L => l)
    : Self[l, R] = base.bimap[l, R](transform)(identity)
