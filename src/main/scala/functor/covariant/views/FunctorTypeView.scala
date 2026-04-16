package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.Functor
import scala.reflect.ClassTag

/** A [[Functor]] that only maps values that have a certain type. */
class FunctorTypeView[+Self[+_], +X, +Active : ClassTag](base: Functor[Self, X])
  extends TypeFunctorView[Self, Any, X, Active](base),
          Functor[[Y] =>> Self[Y | X], Active]
