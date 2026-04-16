package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.projection

import io.github.sgtswagrid.nonsense.functor.bifunctor.ContextBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.ContextFunctor
import scala.annotation.unchecked.uncheckedVariance

/** A view of a [[ContextBifunctor]] that only maps values on the right. */
class ContextBifunctorRightView[+Self[+_, +_], -Context[_], +L, +R]
  (base: ContextBifunctor[Self, Context, L, R])
  extends PartialBifunctorRightView[Self, Any, [l, r] =>> Context[l | r], L, R](
    base,
  ),
          ContextFunctor[
            [X] =>> Self[L, X],
            [X] =>> Context[(L @uncheckedVariance) | X],
            R,
          ]:

  override def map[r : [X] =>> Context[(L @uncheckedVariance) | X]]
    (transform: R => r)
    : Self[L, r] = base.bimap[L, r](identity)(transform)
