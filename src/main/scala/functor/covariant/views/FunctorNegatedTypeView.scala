package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.Functor
import scala.reflect.ClassTag

/** A [[Functor]] that only maps values that don't have a certain type. */
class FunctorNegatedTypeView[+Self[+_], +X, +Inactive : ClassTag]
  (base: Functor[Self, X])
  extends NegatedTypeFunctorView[Self, Any, X, Inactive](base),
          Functor[[Y] =>> Self[Y | Inactive], X]
