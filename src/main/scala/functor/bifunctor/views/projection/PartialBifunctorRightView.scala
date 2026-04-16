package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.projection

import io.github.sgtswagrid.nonsense.functor.bifunctor.PartialBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.PartialFunctor
import scala.annotation.unchecked.uncheckedVariance

/** A view of a [[PartialBifunctor]] that only maps values on the right. */
class PartialBifunctorRightView[
  +Self[+_, +_],
  -RightCodomain,
  -Context[_, _],
  +L,
  +R <: RightCodomain,
]
  (
    base: PartialBifunctor[
      Self,
      L @uncheckedVariance,
      RightCodomain,
      Context,
      L @uncheckedVariance,
      R,
    ],
  )
  extends PartialFunctor[
    [X] =>> Self[L, X],
    RightCodomain,
    [X] =>> Context[L @uncheckedVariance, X],
    R,
  ]:

  override def map[
    r <: RightCodomain : [X] =>> Context[L @uncheckedVariance, X],
  ](transform: R => r): Self[L, r] =
    base.bimap[L @uncheckedVariance, r](identity)(transform)
