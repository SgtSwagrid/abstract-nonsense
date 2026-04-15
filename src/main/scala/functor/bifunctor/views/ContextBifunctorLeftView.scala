package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.functor.bifunctor.ContextBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.ContextFunctor
import scala.annotation.unchecked.uncheckedVariance

/**
  * A view of a [[ContextBifunctor]] that only maps values on the left.
  *
  * Obtainable with [[ContextBifunctor.left]].
  */
class ContextBifunctorLeftView[+Self[+_, +_], -Context[_], +Left, +Right]
  (base: ContextBifunctor[Self, Context, Left, Right])
  extends BoundedContextBifunctorLeftView[Self, Any, Context, Left, Right](
    base,
  ),
          ContextFunctor[
            [X] =>> Self[X, Right],
            [X] =>> Context[X | (Right @uncheckedVariance)],
            Left,
          ]:

  override def map[LeftPost : [X] =>> Context[X | (Right @uncheckedVariance)]]
    (transform: Left => LeftPost)
    : Self[LeftPost, Right] = base.bimap[LeftPost, Right](transform)(identity)
