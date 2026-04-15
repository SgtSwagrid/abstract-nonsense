package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.functor.bifunctor.BoundedBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/**
  * A view of a [[BoundedBifunctor]] that only maps values on the right.
  *
  * Obtainable with [[BoundedBifunctor.right]].
  */
class BoundedBifunctorRightView[
  +Self[+_, +_],
  -RightCodomain,
  +Left,
  +Right <: RightCodomain,
]
  (base: BoundedBifunctor[Self, ? >: Left, RightCodomain, Left, Right])
  extends BoundedContextBifunctorRightView[
    Self, RightCodomain, [_] =>> DummyImplicit, Left, Right
  ](base),
          BoundedFunctor[[X] =>> Self[Left, X], RightCodomain, Right]:

  override protected def mapImpl[RightPost <: RightCodomain]
    (transform: Right => RightPost)
    : Self[Left, RightPost] = base.bimap(identity)(transform)
