package io.github.sgtswagrid.nonsense
package functors.bifunctor.views

import io.github.sgtswagrid.nonsense.functors.bifunctor.RightBoundedBifunctor
import io.github.sgtswagrid.nonsense.functors.covariant.Functor

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
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure (e.g.
  *   [[Int]]).
  */
class BifunctorLeftView[+Self[+_, +_], +Left, +Right]
  (base: RightBoundedBifunctor[Self, ? >: Right, Left, Right])
  extends BoundedBifunctorLeftView[Self, Any, Left, Right](base),
          Functor[[X] =>> Self[X, Right], Left]:

  override protected inline def mapImpl[LeftPost]
    (transform: Left => LeftPost)
    : Self[LeftPost, Right] = base.bimap(transform)(identity)
