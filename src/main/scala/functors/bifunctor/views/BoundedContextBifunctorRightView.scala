package io.github.sgtswagrid.nonsense
package functors.bifunctor.views

import io.github.sgtswagrid.nonsense.functors.bifunctor.BoundedContextBifunctor
import io.github.sgtswagrid.nonsense.functors.covariant.BoundedContextFunctor
import scala.annotation.unchecked.uncheckedVariance

/**
  * A view of a bounded context bifunctor that only maps values on the right.
  * Obtained by calling [[BoundedContextBifunctor.right]].
  *
  * @param base
  *   The underlying structure.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam RightCodomain
  *   The upper bound on [[Right]] following any [[map]]-like operation.
  *
  * @tparam LeftContext
  *   The context bound on [[Left]] required to construct this view.
  *
  * @tparam RightContext
  *   The context bound on [[Right]] that must be present following any
  *   [[map]]-like operation.
  *
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure.
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure.
  */
class BoundedContextBifunctorRightView[
  +Self[+_, +_],
  -RightCodomain,
  -LeftContext[_],
  -RightContext[_],
  +Left,
  +Right <: RightCodomain,
]
  (
    base: BoundedContextBifunctor[
      Self,
      ? >: Left,
      RightCodomain,
      LeftContext,
      RightContext,
      Left,
      Right,
    ],
  )
  (using LeftContext[Left @uncheckedVariance])
  extends BoundedContextFunctor[
    [X] =>> Self[Left, X],
    RightCodomain,
    RightContext,
    Right,
  ]:

  override def map[RightPost <: RightCodomain : RightContext]
    (transform: Right => RightPost)
    : Self[Left, RightPost] = base.bimap(identity)(transform)
