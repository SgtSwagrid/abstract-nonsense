package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.functor.bifunctor.ContextBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.ContextFunctor
import scala.annotation.unchecked.uncheckedVariance

/**
  * A view of a [[ContextBifunctor]] that only maps values on the right.
  *
  * Obtainable with [[ContextBifunctor.right]].
  */
class ContextBifunctorRightView[+Self[+_, +_], -Context[_], +Left, +Right]
  (base: ContextBifunctor[Self, Context, Left, Right])
  extends BoundedContextBifunctorRightView[Self, Any, Context, Left, Right](
    base,
  ),
          ContextFunctor[
            [X] =>> Self[Left, X],
            [X] =>> Context[(Left @uncheckedVariance) | X],
            Right,
          ]:

  override def map[RightPost : [X] =>> Context[(Left @uncheckedVariance) | X]]
    (transform: Right => RightPost)
    : Self[Left, RightPost] = base.bimap[Left, RightPost](identity)(transform)
