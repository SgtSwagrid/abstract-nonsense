package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.functor.bifunctor.BoundedContextBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.PartialFunctor
import scala.annotation.unchecked.uncheckedVariance

/**
  * A view of a [[BoundedContextBifunctor]] that only maps values on the left.
  *
  * Obtainable with [[BoundedContextBifunctor.left]].
  */
class BoundedContextBifunctorLeftView[
  +Self[+_, +_],
  -LeftCodomain,
  -Context[_],
  +Left <: LeftCodomain,
  +Right,
]
  (
    base: BoundedContextBifunctor[
      Self,
      LeftCodomain,
      Right @uncheckedVariance,
      Context,
      Left,
      Right @uncheckedVariance,
    ],
  )
  extends PartialFunctor[
    [X] =>> Self[X, Right],
    LeftCodomain,
    [X] =>> Context[X | (Right @uncheckedVariance)],
    Left,
  ]:

  override def map[
    LeftPost <: LeftCodomain : [X] =>> Context[X | (Right @uncheckedVariance)],
  ](transform: Left => LeftPost): Self[LeftPost, Right] =
    base.bimap[LeftPost, Right @uncheckedVariance](transform)(identity)
