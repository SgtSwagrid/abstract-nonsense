package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.projection

import io.github.sgtswagrid.nonsense.functor.bifunctor.PartialBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.PartialFunctor
import scala.annotation.unchecked.uncheckedVariance

/** A view of a [[PartialBifunctor]] that only maps values on the left. */
class PartialBifunctorLeftView[
  +Self[+_, +_],
  -LeftCodomain,
  -Context[_, _],
  +L <: LeftCodomain,
  +R,
]
  (
    base: PartialBifunctor[
      Self,
      LeftCodomain,
      R @uncheckedVariance,
      Context,
      L,
      R @uncheckedVariance,
    ],
  )
  extends PartialFunctor[
    [X] =>> Self[X, R],
    LeftCodomain,
    [X] =>> Context[X, R @uncheckedVariance],
    L,
  ]:

  override def map[l <: LeftCodomain : [X] =>> Context[X, R @uncheckedVariance]]
    (transform: L => l)
    : Self[l, R] = base.bimap[l, R @uncheckedVariance](transform)(identity)
