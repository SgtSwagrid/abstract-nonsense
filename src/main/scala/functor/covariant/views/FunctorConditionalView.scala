package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.Functor

/** A [[Functor]] that only maps values that satisfy a given condition. */
class FunctorConditionalView[+Self[+_], +X]
  (
    base: Functor[Self, X],
    condition: X => Boolean,
  )
  extends ConditionalFunctorView[Self, Any, X](base, condition),
          Functor[[Y] =>> Self[Y | X], X]
