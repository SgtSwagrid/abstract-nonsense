package io.github.sgtswagrid.nonsense
package functors.bifunctor.views

import io.github.sgtswagrid.nonsense.functors.bifunctor.BoundedBifunctor
import io.github.sgtswagrid.nonsense.functors.covariant.BoundedFunctor

/**
  * A view of a bifunctor that only maps values on the left. Obtained by calling
  * [[BoundedBifunctor.left]].
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
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure (e.g.
  *   [[Int]]).
  */
class BoundedBifunctorLeftView[
  +Self[+_, +_],
  -LeftCodomain,
  +Left <: LeftCodomain,
  +Right,
]
  (base: BoundedBifunctor[Self, LeftCodomain, ? >: Right, Left, Right])
  extends BoundedFunctor[[X] =>> Self[X, Right], LeftCodomain, Left]:

  override protected def mapImpl[LeftPost <: LeftCodomain]
    (transform: Left => LeftPost)
    : Self[LeftPost, Right] = base.bimap(transform)(identity)
