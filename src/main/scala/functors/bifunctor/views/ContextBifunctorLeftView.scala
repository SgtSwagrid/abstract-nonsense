package io.github.sgtswagrid.nonsense
package functors.bifunctor.views

import io.github.sgtswagrid.nonsense.functors.bifunctor.ContextBifunctor
import io.github.sgtswagrid.nonsense.functors.covariant.ContextFunctor
import scala.annotation.unchecked.uncheckedVariance

/**
  * A view of a context bifunctor that only maps values on the left. Obtained by
  * calling [[ContextBifunctor.left]].
  *
  * @param base
  *   The underlying structure.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
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
class ContextBifunctorLeftView[
  +Self[+_, +_],
  -LeftContext[_],
  -RightContext[_],
  +Left,
  +Right,
]
  (
    base: ContextBifunctor[
      Self,
      LeftContext,
      RightContext,
      Left,
      Right,
    ],
  )
  (using RightContext[Right @uncheckedVariance])
  extends BoundedContextBifunctorLeftView[
    Self,
    Any,
    LeftContext,
    RightContext,
    Left,
    Right,
  ](base),
          ContextFunctor[[X] =>> Self[X, Right], LeftContext, Left]:

  override def map[LeftPost : LeftContext]
    (transform: Left => LeftPost)
    : Self[LeftPost, Right] = base.bimap(transform)(identity)
