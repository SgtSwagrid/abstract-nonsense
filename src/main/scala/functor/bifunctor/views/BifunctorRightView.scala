package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.functor.bifunctor.LeftBoundedBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.Functor

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
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure (e.g.
  *   [[Int]]).
  */
class BifunctorRightView[+Self[+_, +_], +Left, +Right]
  (base: LeftBoundedBifunctor[Self, ? >: Left, Left, Right])
  extends BoundedBifunctorRightView[Self, Any, Left, Right](base),
          Functor[[X] =>> Self[Left, X], Right]:

  override protected inline def mapImpl[RightPost]
    (transform: Right => RightPost)
    : Self[Left, RightPost] = base.bimap(identity)(transform)
