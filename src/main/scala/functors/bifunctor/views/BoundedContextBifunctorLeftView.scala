package io.github.sgtswagrid.nonsense
package functors.bifunctor.views

import io.github.sgtswagrid.nonsense.functors.bifunctor.BoundedContextBifunctor
import io.github.sgtswagrid.nonsense.functors.covariant.BoundedContextFunctor
import scala.annotation.unchecked.uncheckedVariance

/**
  * A view of a bounded context bifunctor that only maps values on the left.
  * Obtained by calling [[BoundedContextBifunctor.left]].
  *
  * @param base
  *   The underlying structure.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam LeftCodomain
  *   The upper bound on [[Left]] following any [[map]]-like operation.
  *
  * @tparam LeftContext
  *   The context bound on [[Left]] that must be present following any
  *   [[map]]-like operation.
  *
  * @tparam RightContext
  *   The context bound on [[Right]] required to construct this view.
  *
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure.
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure.
  */
class BoundedContextBifunctorLeftView[
  +Self[+_, +_],
  -LeftCodomain,
  -LeftContext[_],
  -RightContext[_],
  +Left <: LeftCodomain,
  +Right,
](
  base: BoundedContextBifunctor[
    Self,
    LeftCodomain,
    ? >: Right,
    LeftContext,
    RightContext,
    Left,
    Right,
  ]
)(using RightContext[Right @uncheckedVariance])
  extends BoundedContextFunctor[[X] =>> Self[X, Right], LeftCodomain, LeftContext, Left]:

  override def map[LeftPost <: LeftCodomain : LeftContext]
    (transform: Left => LeftPost)
    : Self[LeftPost, Right] = base.bimap(transform)(identity)
