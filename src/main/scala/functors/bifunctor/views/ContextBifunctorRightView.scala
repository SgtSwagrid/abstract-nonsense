package io.github.sgtswagrid.nonsense
package functors.bifunctor.views

import io.github.sgtswagrid.nonsense.functors.bifunctor.ContextBifunctor
import io.github.sgtswagrid.nonsense.functors.covariant.ContextFunctor
import scala.annotation.unchecked.uncheckedVariance

/**
  * A view of a context bifunctor that only maps values on the right. Obtained
  * by calling [[ContextBifunctor.right]].
  *
  * @param base
  *   The underlying structure.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
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
class ContextBifunctorRightView[
  +Self[+_, +_],
  -LeftContext[_],
  -RightContext[_],
  +Left,
  +Right,
](
  base: ContextBifunctor[Self, LeftContext, RightContext, Left, Right]
)(using LeftContext[Left @uncheckedVariance])
  extends BoundedContextBifunctorRightView[Self, Any, LeftContext, RightContext, Left, Right](base),
          ContextFunctor[[X] =>> Self[Left, X], RightContext, Right]:

  override def map[RightPost : RightContext]
    (transform: Right => RightPost)
    : Self[Left, RightPost] = base.bimap(identity)(transform)
