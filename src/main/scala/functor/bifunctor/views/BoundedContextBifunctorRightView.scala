package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.functor.bifunctor.BoundedContextBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.PartialFunctor
import scala.annotation.unchecked.uncheckedVariance

/**
  * A view of a [[BoundedContextBifunctor]] that only maps values on the right.
  *
  * Obtainable with [[BoundedContextBifunctor.right]].
  */
class BoundedContextBifunctorRightView[
  +Self[+_, +_],
  -RightCodomain,
  -Context[_],
  +Left,
  +Right <: RightCodomain,
]
  (
    base: BoundedContextBifunctor[
      Self,
      Left @uncheckedVariance,
      RightCodomain,
      Context,
      Left @uncheckedVariance,
      Right,
    ],
  )
  extends PartialFunctor[
    [X] =>> Self[Left, X],
    RightCodomain,
    [X] =>> Context[(Left @uncheckedVariance) | X],
    Right,
  ]:

  override def map[
    RightPost <: RightCodomain : [X] =>> Context[(Left @uncheckedVariance) | X],
  ](transform: Right => RightPost): Self[Left, RightPost] =
    base.bimap[Left @uncheckedVariance, RightPost](identity)(transform)
