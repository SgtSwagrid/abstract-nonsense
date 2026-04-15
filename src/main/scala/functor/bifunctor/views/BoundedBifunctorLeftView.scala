package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.functor.bifunctor.BoundedBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/**
  * A view of a [[BoundedBifunctor]] that only maps values on the left.
  *
  * Obtainable with [[BoundedBifunctor.left]].
  */
class BoundedBifunctorLeftView[
  +Self[+_, +_],
  -LeftCodomain,
  +Left <: LeftCodomain,
  +Right,
]
  (base: BoundedBifunctor[Self, LeftCodomain, ? >: Right, Left, Right])
  extends BoundedContextBifunctorLeftView[
    Self, LeftCodomain, [_] =>> DummyImplicit, Left, Right
  ](base),
          BoundedFunctor[[X] =>> Self[X, Right], LeftCodomain, Left]:

  override protected def mapImpl[LeftPost <: LeftCodomain]
    (transform: Left => LeftPost)
    : Self[LeftPost, Right] = base.bimap(transform)(identity)
