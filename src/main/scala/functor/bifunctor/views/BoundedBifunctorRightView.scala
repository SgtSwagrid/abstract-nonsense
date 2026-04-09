package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.functor.bifunctor.BoundedBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/**
  * A view of a bifunctor that only maps values on the right. Obtained by
  * calling [[BoundedBifunctor.right]].
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
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure (e.g.
  *   [[Int]]).
  */
class BoundedBifunctorRightView[
  +Self[+_, +_],
  -RightCodomain,
  +Left,
  +Right <: RightCodomain,
]
  (base: BoundedBifunctor[Self, ? >: Left, RightCodomain, Left, Right])
  extends BoundedFunctor[
    [X] =>> Self[Left, X],
    RightCodomain,
    Right,
  ]:

  override protected def mapImpl[RightPost <: RightCodomain]
    (transform: Right => RightPost)
    : Self[Left, RightPost] = base.bimap(identity)(transform)
